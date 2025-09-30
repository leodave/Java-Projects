# ChainResource Java Project

## Overview

This project implements a generic resource retrieval system called "ChainResource"
that manages a chain of storage layers to efficiently cache and retrieve data of type `T`.
It automatically handles expiration and propagation of values through multiple storages such as
in-memory cache, local file system, and web-API(for local testing represented by ManualStrorage) .

For Build

- maven clean install using java 11+

local testing

- Maven project structure with unit tests using JUnit 5