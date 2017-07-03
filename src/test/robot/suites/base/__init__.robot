# -*- coding: robots -*-
#
# Example initialization file
*** Settings ***
Documentation           An example test suite for the Skeleton website.
...                     It tries to contain all important informations
...                     to get a developer / tester/ PO started with testing.

Library                 BuiltIn
Metadata                Version 0.1
Suite Setup             Setup Skeleton Suite
Suite Teardown          Teardown Skeleton Suite
Force Tags              example
Test Setup
Test Teardown
Test Timeout

*** Variables ***
${MESSAGE}      Hello, world!
${OUTPUT DIR}   log

*** Keywords ***
Setup Skeleton Suite
    Log     ${MESSAGE}

Teardown Skeleton Suite
    Log     ${MESSAGE}
