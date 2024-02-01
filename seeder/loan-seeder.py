#!/usr/bin/env python3

import csv
import re
import requests
import json

month_map = {"JAN": 1,"FEB": 2,"MAR": 3,"APR": 4,"MAY": 5,"JUN": 6,"JUL": 7,"AUG": 8,"SEP": 9, "OCT": 10, "NOV": 11, "DEC": 12}
url = "http://host.docker.internal:8080/loan"

with open('LoanStats_securev1_2017Q4.csv', 'r') as file:
    reader = csv.DictReader(file)
    for row in reader:
        issue_date = row["issue_d"]
        state = row["addr_state"]
        regex = re.compile(r'(?P<month>[aA-zZ]{3})-(?P<year>\d{4})')
        matches = regex.match(issue_date)
        year = int(matches.group("year"))
        month = int(month_map[matches.group("month").upper()])
        grade = row["grade"]
        d = {
            "year": year,
            "month": month,
            "grade": grade,
            "state": state
        }
        response = requests.post(url, data = json.dumps(d))
        if response.status_code != 200:
            print(f"An error occured for payload {d} and returned status {response.status_code}")