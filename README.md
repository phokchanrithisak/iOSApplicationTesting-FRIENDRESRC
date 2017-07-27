# iOSXCUITest
**KEEN Template** to automate iOS Application **_Test_** with [Appium](http://appium.io/), XCUITest, WebDriverAgent, [Selenium](http://www.seleniumhq.org/), [Cucumber](https://cucumber.io/), [TestNG](http://testng.org/doc/), [Junit Test](http://junit.org/junit4/) and [Apache Maven](https://maven.apache.org/).

It **handles** almost all the headache in starting new automated testing project with `java` as the client library.
**You just have to start coding, then *run* your _test framework_ from either _Intellij IDEA_ or _command prompt_ or 
_terminal_**.

By the help of [maven-cucumber-reporting](https://mvnrepository.com/artifact/net.masterthought/maven-cucumber-reporting/0.0.2), result of the tests will be _generated automatically_ with the awesome bootstrap-based html.

## Documentation

### Prerequisite
This iOSXCUITest template use [Cucumber](https://cucumber.io/) framework with [Gherkin](https://cucumber.io/docs/reference) to get the advantages of `.feature` files.
- If you are not familiar with Gherkin, please refer <https://cucumber.io/docs/reference> for language concepts and its syntax.
- Check your **Java JDK**, by run `java -version` in command prompt(Windows) or terminal(macOS). If you could see the version of java, then it has been installed, otherwise, please install it based on your operating system.**Make Sure you have set `JAVA_HOME` to `jdk` folder and `jdk\bin` to `PATH`.**
- For **macOS**, please install **XCODE** from AppStore first, then install **homebrew** by opening terminal and run `ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`. Once the installation finished, run `brew install node`
- Make sure that you have maven installed by run `mvn -v` in terminal(macOS). If you could see the version of maven, then it has been installed, otherwise, please install it based on your operating system. (refer [here](https://maven.apache.org/install.html))
- IDE requirement is [Intellij Idea](https://www.jetbrains.com/idea/); either the Ultimate or the Community one would be fine.
### XCUITest Configuration for iOS 9.3.x or Later
(Credit to <https://www.youtube.com/watch?v=ySglJIrDVMQ&t=2071s> for the awesome tutorial of appium with XCUITest configuration)

Because **apple** has disable UIAutomator for 9.3.x or later, the alternate way to test iOS with higher platform version is to use XCUITest with WebDriverAgent.
- Open your terminal, and run
  - `sudo npm install -g appium` to install appium (If you are not sure whether you have appium server installed or not, run `appium -v`, if you could see appium version, then it has been installed. Make sure that your appium version is later that 1.6, otherwise, run `sudo npm install -g appium@1.6.5`. You can change @1.6.5 to the lattest version as desired)
  - `brew install ideviceinstaller`
  - `brew install carthage`
  - `npm install -g ios-deploy`
  - `npm install -g deviceconsole`
  - `sudo gem install xcpretty`
  - `brew install libimobiledevice --HEAD`
  - `sudo npm install -g appium-doctor`
- Configure WebDriverAgent
  - in the terminal :
    - run `cd /usr/local/lib/node_modules/appium/node_modules` and run `sudo chmod -R 777 . Appium-xcuit-driver/*` to give access to all folder inside
    - run `cd /usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/`
    - run `sudo mkdir -p Resources/WebDriverAgent.bundle`
    - run `sudo sh ./Scripts/bootstrap.sh -d`
    - run `open .`
   - On the opening Finder that pop up after running *open .*
    - You will see **WebDriverAgent.xcodeproj**, right click and open it with **XCode**
    - **From now on, it requires interfaces to follow**. **_Follow_** this [youtube tutorial](https://www.youtube.com/watch?v=ySglJIrDVMQ&t=2071s) from [Tech Tock Tech](https://www.youtube.com/channel/UCaJA59vxBDS7smS81Gqf7KA) at minute of **19:52** onward.
After the **integration app** is built successfully. You are good to go.
  
### Getting Started
Let's get our hand dirty.
- If you are _new_ to Intellij IDEA and you haven't got **Git _installed_** on your machine, please kindly download it from the official **git website** [here](https://git-scm.com/downloads). After git installation, you are good to go.
- Launch Intellij IDEA, 
  - if you are on **Welcome Screen**, choose `check out from version control` and in the dropdown, choose `Git`.
  - if you are not, on Intellij Menu Bar, choose **File** then **New** and click **Project On Version Control** and finally choose **Git** from the dropdown.
- On **Clone Repository** Window, 
  - **copy** <https://github.com/chhatrachhorm/iOSXCUITest.git> into **Git Repository URL**
  - specify **Parent Directory** and **Directory Name** and click **clone**
### Template Exploration
There should be **four packages** under `src\test\java` directory.
Each of them play a vital role.
- `BaseUtil class` under **Base package** is written to declare `MobileDriver<IOSElement>`
- **Features package** is to wrap all feature files which are used to describe _Behavorial Driven Developement_ concepts
- **POM package** is created to wrap all POM's classes that define the **elements** and the **methods** performing in a _page_.
- `TestRunner` is written to specify **test type _(junit or testng)_** and to specify the **output** directory
- `Steps packages` can have only one `Hook class` to *initialize* and *terminate* `WebDriver` but it has endless number of `step classes` based on the steps specifying in `.feature` files

The **target directory** which contains _test result_ will be created once you have run the tests.

### Configuration
**Most of the configurations are specified in the template itself via _comments_.**
But Do not forget to
- Copy your `.app` file into `src\test\Res` with a clear and shorter name.
- Configure the variables in **`src\test\java\Steps\Hook.java`** (See examples and suggestios in the **Hook.java** itself)

**After this configuration, you now can define your `.feature` files as many as desired, and define the steps in `src\test\java\Steps` in many classes as prefered.**

### Automate TestCases
Before you begin, open **terminal** and run `appium`. Then, you have to **start a simulator or a real device connection**.(If you do not have simulator open, go to **finder** and then use **cmd + shift ** g** to navigate to `/Applications/Xcode.app/Contents/Developer/Applications/` and finally, open `Simulator.app`)
In `src\test\java\Runner\TestRunner.java`, the default set up is good to go already. *Right Click* and choose **Run TestRunner**. By doing this, **the test result will be generated automatically by cucumber-testng.**

To get [maven-cucumber-reporting](https://mvnrepository.com/artifact/net.masterthought/maven-cucumber-reporting/0.0.2), you have to **run the test from terminal**.
- if you do not have maven on your system, please kindly install it.
- Launch terminal, **navigate** to the project you are working on.
- Run `mvn clean` to clean the previous test target folder
- Run `mvn verify` to **automate the test**

The result will be generated in `target/kit-keen-iOSXCUITest-html/cucumber-html-reports`.**Nota Bene:** the `kit-keen-iOSXCUITest-html` might be changed if you changed the folder name in the configuartion.
