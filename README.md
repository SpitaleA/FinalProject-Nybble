# Nybble-AutomationWeb

## Reporting
### Comands
- allure serve .\build\allure-results\

## Browser options
### Run headless Chrome
- ChromeOptions options = new ChromeOptions();
  options.addArguments("--headless");
### Maximize Window
- driver.manage().window().maximize(); # Selenium-CleanStructure

# Gradle commands
- gradle clean -x test -PenvFile=qa.properties tests
 Ejecuta los test con el @tag SmokeTest y settea 
 envFile el archivo qa.rpoperties
- gradle clean test -PenvFile=qa.properties --tests "TestSuite1"# FinalProject-Nybble

# Browser devtool utils
 - ctrl + shift + p : opens cmd for devtools