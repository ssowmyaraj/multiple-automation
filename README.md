## Multiple Web and API Automation Tests

## Installation 
   
   - Clone the repository
     `git clone git@github.com:ssowmyaraj/multiple-automation.git`
     `cd multiple-automation`
    
## Set up
   
    - Import the project as existing maven project in the IDE 
    
## Run command 
    Navigate to the project folder where testng.xml and pom.xml files are present
    
    For WEB
    - mvn test -Pfull-suite -Dgroups="multipleweb" -Dpassword="" -Dplatform="web"  
    For API
    - mvn test -Pfull-suite -Dgroups="multipleapi" -Dpassword="" -Dplatform="api"

    OR Run using the testng.xml file
    
