# -*- coding: robots -*-

*** Settings ***
Documentation           Skeleton resource file
Library                 Selenium2Library

*** Variables ***
${BROWSER}    Chrome
${BASE_URL}   http://localhost:8081

*** Keywords ***
Open Browser To Home Page
    Open Browser        ${BASE_URL}     ${BROWSER}

Browser is opened to the skeleton home page
    Open Browser        ${BASE_URL}     ${BROWSER}

Browser should be closed
    Close Browser
