
# Selenium Automation Framework

## Introduction
Welcome to Falcon-X, a powerful and versatile Selenium Java framework designed to streamline your automated testing process. Built with TestNG and Maven, Falcon-X empowers developers and testers to create robust, scalable, and maintainable test suites for web applications.

## Features
- **TestNG Integration:** Seamlessly leverage the power of TestNG to organize and execute your test cases efficiently.
- **Maven Support:** Simplify dependency management and project setup with Maven, ensuring smooth integration into your development workflow.
- **Flexible Configuration:** Customize test execution, environment setup, and reporting to suit your specific requirements.
- **Page Object Model (POM):** Implement the industry-standard Page Object Model for cleaner, more maintainable test code. [Coming soon...]
- **Cross-Browser Testing:** Run Chrome, Edge, or Firefox locally and in Jenkins.
- **API Testing:** Execute REST API smoke tests with Rest Assured and TestNG.
- **Multi-site UI Smoke Tests:** Validate the automation demo site and Quick Reference site.
- **Parallel Execution:** Optimize test execution time by running tests in parallel, maximizing efficiency without compromising accuracy. [Coming soon...]
- **Reporting:** Generate comprehensive test reports with rich insights, enabling stakeholders to make informed decisions.
- **Extensibility:** Easily extend and enhance the framework with additional features and integrations as needed.

## Getting Started
To start using Falcon-X, follow these simple steps:

1. **Clone the Repository:**
   ```
   https://github.com/Simhadri07/SeleniumAutomationFramework.git
   ```

2. **Install Dependencies:**
   ```
   mvn clean install
   ```

3. **Configure Tests:**
   Browser and CI settings are controlled with Maven properties:
   ```
   mvn clean test -Dbrowser=chrome -Dheadless=false
   mvn clean test -Dbrowser=edge -Dheadless=true
   mvn clean test -DapiBaseUrl=https://jsonplaceholder.typicode.com
   ```
   `browser` defaults to Chrome. Headless mode defaults to true on Jenkins and false locally.
   The project uses JDK 21.
   API tests use the public JSONPlaceholder API by default and cover collection reads,
   filtering, user lookup, create, update, patch, delete, and 404 behavior. Override the
   API host with `-DapiBaseUrl` or `API_BASE_URL`.
   The `quickRef` repository's live site is configured as `https://cheatsheets.zip/`.
   `https://github.com/Simhadri07/devpad-Pro` currently returns 404 and is not included as a
   passing test target until the repository is made available.

   Credentials should be supplied as `TEST_USERNAME` and `TEST_PASSWORD` environment variables
   (or `-Dusername` and `-Dpassword`). Copy `src/main/resources/credentials.properties.example`
   only for local development; do not commit real credentials.

4. **Write Test Cases:**
   Implement test cases using the Page Object Model approach for better organization and maintenance.

5. **Execute Tests:**
   ```
   mvn test
   ```

6. **Review Reports:**
   After each execution, open `target/extent-reports/ExtentReport.html` for the Extent report.
   Framework logs are written to `target/logs/automation.log` and rotated automatically.
   Failed UI tests include a screenshot in the Extent report under `target/screenshots/`.

7. **Jenkins:**
   Create a Pipeline job using the included `Jenkinsfile`, configure the Jenkins credential ID
   `selenium-test-credentials`, and ensure the agent has Java, Maven, and Chrome or Edge installed.

8. **Extend and Customize:**
   Explore the framework's architecture to add new features or integrate additional tools to further enhance your testing capabilities.

## Contributing
We welcome contributions from the community to improve Falcon-X. Whether it's reporting bugs, suggesting new features, or submitting pull requests, your input is valuable in making this framework even better.

## License
This project is licensed under the [APACHE 2.0](LICENSE), allowing for unrestricted use, modification, and distribution.

## Contact
Have questions, suggestions, or feedback? Feel free to reach out to us at [schatla36@gmail.com](mailto:schatla36@gmail.com) or create Pull Request on GitHub.

---

Feel free to customize this template according to your framework's specific features and requirements. Happy testing!
