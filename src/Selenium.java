import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.*;
public class Selenium {

    public static void main(String[] args) throws IOException {

        FileInputStream fstream = new FileInputStream("C:\\Users\\aameli1376\\IdeaProjects\\HW4_softwareTest\\searchInput\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        String input[] = new String[10];
        boolean checked = true;
        String[] result =
                {"/html/body/main/div[2]/div/div/div/div[2]/div[1]/article/div[1]/ul/li[1]/div/div[2]/div[1]/div[1]/a"
                ,"/html/body/main/div[2]/div/div/div/div[2]/div[1]/article/div[1]/ul/li[2]/div/div[2]/div[1]/div[1]/a"
                ,"/html/body/main/div[2]/div/div/div/div[2]/div[1]/article/div[1]/ul/li[3]/div/div[2]/div[1]/div[1]/a"
                ,"/html/body/main/div[2]/div/div/div/div[2]/div[1]/article/div[1]/ul/li[4]/div/div[2]/div[1]/div[1]/a"
                ,"/html/body/main/div[2]/div/div/div/div[2]/div[1]/article/div[1]/ul/li[5]/div/div[2]/div[1]/div[1]/a"
                };

        int i=0;
        while ((strLine = br.readLine()) != null)   {
            input[i] = strLine;
            i++;
        }
        fstream.close();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\aameli1376\\Downloads\\Compressed\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.digikala.com/");
        System.out.println("Successfully launched");

        for (int j=0;j<10;j++){
            WebElement searchBox = driver.findElement(new By.ByXPath("/html/body/header/div/div/div[2]/div/input"));
            WebElement submitButton = driver.findElement(new By.ByXPath("/html/body/header/div/div/div[2]/div/button"));
            searchBox.sendKeys(input[j]);
            submitButton.click();
            //tokenization
            String[] tokenized = input[j].split("\\s+");
            /*
            for (String s : tokenized) {
                System.out.println(s);
            }

             */
            System.out.println("-----------------------------------------------------");
            System.out.println("Query "+j+" : "+input[j]);
            for (int k=0;k<5;k++) {
                WebDriverWait wait = new WebDriverWait(driver, 10);
                WebElement messageElement = wait.until(
                        ExpectedConditions.presenceOfElementLocated(new By.ByXPath(result[k]))
                );

                String message = messageElement.getText();
                System.out.println("result "+k+" : "+message);

                for (String s : tokenized) {
                    if (message.contains(s)) {
                        checked = true;

                    }else {
                        checked = false;
                        break;
                    }

                }
                if (checked)
                {
                    System.out.println("<< in the  result "+k+" all search's words found >>");
                }
            }

        }
        driver.quit();
    }
}



