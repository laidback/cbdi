# -*- coding: robots -*-

*** Settings ***
Documentation           Skeleton test file
Library                 Selenium2Library
Resource                resource.robot

*** Test Cases ***
Open Skeleton App
    [Tags]  Smoke
    Open Browser To Home Page
    [Teardown]  Close Browser

Check header of Skeleton App
    Open Browser To Home Page
    Element Text Should Be  identifier=header    Yeah    NotRightTheStory 
    [Teardown]  Close Browser

Scenario: Opening the home page
    Given Browser is opened to the skeleton home page
    Then Browser should be closed

