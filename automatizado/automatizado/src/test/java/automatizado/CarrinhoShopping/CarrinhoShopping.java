package automatizado.CarrinhoShopping;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class CarrinhoShopping {

    private WebDriver driver;


    @Given("que acessei um site de compras")
    public void queAcesseiUmSiteDeCompras() throws InterruptedException {
        try {
            System.setProperty("webdriver.chrome.driver", "src/test/java/automatizado/resource/chromedriver_138.0.7204.94.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://advantageonlineshopping.com/#/");
        } catch (Exception e) {
            Thread.sleep(5000);
            driver.get("https://advantageonlineshopping.com/#/");
        }


    }

    @When("pesquisar um produto")
    public void pesquisarUmProduto() {
        WebDriverWait clicarPesquisar = new WebDriverWait(driver, 30);
        clicarPesquisar.until(ExpectedConditions.elementToBeClickable(By.id("menuSearch"))).click();
        WebElement pesquisarProduto = driver.findElement(By.xpath("//input[@id='autoComplete']"));
        pesquisarProduto.sendKeys("HP ELITEPAD 1000 G2 TABLET" + Keys.ENTER);
        WebDriverWait clicarProduto = new WebDriverWait(driver, 30);
        clicarProduto.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[.='HP ElitePad 1000 G2 Tablet'])[2]"))).click();


    }

    @Then("devo incluir o mesmo no carrinho")
    public void devoIncluirOMesmoNoCarrinho() {
        WebDriverWait clicarCarrinho = new WebDriverWait(driver, 20);
        clicarCarrinho.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='save_to_cart']"))).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement conferirProduto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3[contains(.,'HP ELITEPAD 1000 G2 TABLET')])[1]")));
        String produtoCarrinho = conferirProduto.getAttribute("textContent");
        String produtoEsperado = "HP ELITEPAD 1000 G2 TABLET";
        if (produtoCarrinho.equals(produtoEsperado)) {
            System.out.println("Produto está correto");
        } else {
            Assert.fail("Produto não está correto. Produto escolhido: " + produtoCarrinho);

        }
        driver.close();
    }
}







