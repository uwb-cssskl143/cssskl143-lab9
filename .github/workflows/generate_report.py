import xml.etree.ElementTree as ET
import os
import re
import datetime
import json


# def parse_java_file(file_path):
#     with open(file_path, 'r') as file:
#         content = file.read()

#     # Regex pattern to match Java methods (simplified version)
#     pattern = r'(public|private|protected|static)\s+\w+\s+(\w+)\s*\([^)]*\)\s*\{([^}]*)\}'
#     matches = re.finditer(pattern, content, re.MULTILINE | re.DOTALL)

#     methods = {}
#     for match in matches:
#         method_name = match.group(2)
#         method_body = match.group(3)
#         # Count the lines in the method body, adjusting for the method's opening and closing braces
#         line_count = method_body.count('\n') + 1
#         methods[method_name] = line_count

#     return methods

def parse_java_files(directory):
    java_files = [f for f in os.listdir(directory) if f.endswith('.java')]
    classes = {}

    for java_file in java_files:
        with open(os.path.join(directory, java_file), 'r') as file:
            content = file.read()

        pattern = r'(public|private|protected|static)\s+\w+\s+(\w+)\s*\([^)]*\)\s*\{([^}]*)\}'
        matches = re.finditer(pattern, content, re.MULTILINE | re.DOTALL)

        class_name = java_file[:-5]
        methods = {}
        for match in matches:
            method_name = match.group(2)
            method_body = match.group(3)
            line_count = method_body.count('\n') + 1
            methods[method_name] = line_count

        classes[class_name] = methods

    return classes


def check_requirements(parsed_files, requirements_file):
    results = []
    with open(requirements_file, 'r') as file:
        requirements = json.load(file)

    for class_name, methods in parsed_files.items():
        if class_name in requirements:
            for method_name, line_count in methods.items():
                if method_name in requirements[class_name]:
                    required_line_count = requirements[class_name][method_name]
                    if line_count < required_line_count:
                        results.append(" -" + class_name.upper() + " CLASS: " + method_name + " does not display full effort")

    return results

def read_xml_to_text(input_directory, output_file):
    with open(output_file, 'w') as outfile:
        for filename in os.listdir(input_directory):
            if filename.endswith('.xml'):
                filepath = os.path.join(input_directory, filename)
                with open(filepath, 'r') as file:
                    for line in file:
                        outfile.write(line)

def parse_junit_xml(directory):
    results = {"total": 0, "passed": 0, "failed": 0, "skipped": 0, "missing": [], "incorrect": []}
    for file in os.listdir(directory):
        if file.endswith(".xml"):
            path = os.path.join(directory, file)
            tree = ET.parse(path)
            root = tree.getroot()
            for testcase in root.findall(".//testcase"):
                results["total"] += 1
                failure = testcase.find("failure")
                if testcase.find("failure") is not None:
                    results["failed"] += 1
                    if failure.get('message').startswith("MISSING:"):
                        results["missing"].append(failure.get('message')[8:])
                    else:
                        results["incorrect"].append(failure.get('message'))
                elif testcase.find("skipped") is not None:
                    results["skipped"] += 1
                else:
                    results["passed"] += 1
    return results

def parse_junit_xml_effort(directory):
    results = ""
    for file in os.listdir(directory):
        if file.endswith(".xml"):
            path = os.path.join(directory, file)
            tree = ET.parse(path)
            root = tree.getroot()
            for testcase in root.findall(".//testcase"):
                for failure in testcase.findall('failure'):
                    if 'NoSuchMethodException' in failure.get('type') or 'NoSuchMethodException' in failure.text:
                        results += failure.text + "\n"
    return results

def parse_pmd_xml(directory):
    results = {}
    pmd_file = os.path.join(directory, "pmd.xml")
    if os.path.isfile(pmd_file):
        tree = ET.parse(pmd_file)
        root = tree.getroot()
        namespace = {'ns': 'http://pmd.sourceforge.net/report/2.0.0'}
        for file in root.findall("ns:file", namespace):
            filename = os.path.basename(file.get('name'))
            results[filename] = []
            for violation in file.findall('ns:violation', namespace):
                violation_obj = {
                    "text": violation.text.strip(),
                    "line": violation.get('beginline')
                }
                results[filename].append(violation_obj)
    return results

def generate_report(results, effort_results, pmd_results, output_file):
    with open(output_file, "w") as f:

        timestamp = datetime.datetime.now()
        f.write(f"Report generated on {timestamp}\n\n")

        f.write("Test Results Summary\n")
        f.write("===================\n")
        f.write(f"Total Tests: {results['total']}\n")
        f.write(f"Passed: {results['passed']}\n")
        f.write(f"Failed: {results['failed']}\n")
        f.write(f"Skipped: {results['skipped']}\n")
        f.write("\n")
        f.write("Effort Not Displayed\n")
        f.write("=============\n")
        for missing in results["missing"]:
            f.write(missing + "\n")
        f.write("\n")
        for method in effort_results:
            f.write(method + "\n")
        f.write("\n")
        f.write("Incorrect Tests\n")
        f.write("===============\n")
        for incorrect in results["incorrect"]:
            f.write(incorrect + "\n")
        f.write("\n")
        f.write("CODE FEEDBACK\n")
        f.write("===========\n")
        for file, violations in pmd_results.items():
            f.write(f"File: {file}\n")
            for violation in violations:
                f.write(f"Line {violation['line']}: {violation['text']}\n")
            f.write("\n")

def generate_failed_report(output_file):
    with open(output_file, "w") as f:

        timestamp = datetime.datetime.now()
        f.write(f"Report generated on {timestamp}\n\n")

        f.write("Submitted code could not compile and therfore can not be tested\n")
        f.write("Please fix the compilation errors and resubmit\n")


xml_directory = 'target/surefire-reports'
output_text_file = 'report.txt'

if not os.path.isdir(xml_directory):
    generate_failed_report(output_text_file)
else:
    results = parse_junit_xml(xml_directory)
    pmd_results = parse_pmd_xml('target')
    methods = parse_java_files('src/main/java')
    effort_results = check_requirements(methods, '.github/workflows/lineLengthRequirements.json')
    generate_report(results, effort_results, pmd_results, output_text_file)
