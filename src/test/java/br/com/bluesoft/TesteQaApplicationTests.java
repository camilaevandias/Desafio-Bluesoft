package br.com.bluesoft;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;



@SpringBootTest
class TesteQaApplicationTests {
	private static WebDriver driver;
	public static final String urlBase = "http://localhost:8080/?";
	public static final String caminhoDriver = "src/main/resources/drivers/chromedriver.exe";

		@BeforeAll
		public static void beforeAll(){
			System.setProperty("webdriver.chrome.driver", caminhoDriver);
			//driver = new ChromeDriver();
			//driver.manage().window().maximize();
			//driver.get(urlBase);
			//driver.navigate().to (urlBase);
		}

		@BeforeEach
		public void beforeEach() {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(urlBase);
			//driver.navigate().to (urlBase);
		}

		@AfterEach
		public void afterEach() {
			driver.quit();
		}

		// TESTES DE PREENCHIMENTO INDIVIDUAL DOS CAMPOS
		@Test
		void TC001_NaoDeveAceitarNumeroNoPreenchimentoDoCampoNomeCompleto() {
			driver.findElement(By.id("nome")).click();
			driver.findElement(By.id("nome")).sendKeys("123456");		
		}

		@Test
		void TC002_DeveAceitarLetrasNoPreenchimentoDoCampoNomeCompleto() {
			driver.findElement(By.id("nome")).click();
			driver.findElement(By.id("nome")).sendKeys("Fulano da Silva");
		
		}

		@Test
		void TC003_NaoDeveAceitarMenosDe11DigitosNoPreenchimentoDoCampoCPF() {
			driver.findElement(By.id("cpf")).click();
			driver.findElement(By.id("cpf")).sendKeys("1254");
			driver.findElement(By.id("celular")).click();
			assertEquals(driver.switchTo().alert().getText(),"CPF Inválido! Necessário 11 digitos.");	
		}

		@Test
		void TC004_NaoDeveAceitarMaisDe11DigitosNoPreenchimentoDoCampoCPF() {
			driver.findElement(By.id("cpf")).click();
			driver.findElement(By.id("cpf")).sendKeys("1254123456789");
			driver.findElement(By.id("celular")).click();
			assertEquals(driver.switchTo().alert().getText(),"CPF Inválido! Necessário 11 digitos.");	
		}

		@Test
		void TC005_DeveAutoFormatarOsNumerosNoPreenchimentoCorretoDoCampoCPF() {
			driver.findElement(By.id("cpf")).click();
			driver.findElement(By.id("cpf")).sendKeys("42693231833");
			driver.findElement(By.id("celular")).click();
			assertEquals("426.932.318-33", "426.932.318-33");
		}

		@Test
		void TC006_NaoDeveAceitarMenosDe11DigitosNoPreenchimentoDoCampoCelular() {
			driver.findElement(By.id("celular")).click();
			driver.findElement(By.id("celular")).sendKeys("1254");
			driver.findElement(By.id("dt-nascimento")).click();
			assertEquals(driver.switchTo().alert().getText(),"Celular incorreto! Digite 11 números.");
		}

		@Test
		void TC007_NaoDeveAceitarMaisDe11DigitosNoPreenchimentoDoCampoCelular() {
			driver.findElement(By.id("celular")).click();
			driver.findElement(By.id("celular")).sendKeys("1254123456789");
			driver.findElement(By.id("dt-nascimento")).click();
			assertEquals(driver.switchTo().alert().getText(),"Celular incorreto! Digite 11 números.");
		}

		@Test
		void TC008_DeveAutoFormatarOsNumerosNoPreenchimentoCorretoDoCampoCelular() {
			driver.findElement(By.id("celular")).click();
			driver.findElement(By.id("celular")).sendKeys("11979776994");
			driver.findElement(By.id("dt-nascimento")).click();
			assertEquals("(11) 97977-6994", "(11) 97977-6994");
		}

		@Test
		void TC009_NaoDeveAceitarLetrasNoPreenchimentoDoCampoDataDeNascimento() {
			driver.findElement(By.id("dt-nascimento")).click();
			driver.findElement(By.id("dt-nascimento")).sendKeys("abcdef");
			assertEquals("", "");
		}

		@Test
		void TC010_DeveAceitarNumerosNoPreenchimentoCorretoDoCampoDataDeNascimento() {
			driver.findElement(By.id("dt-nascimento")).click();
			driver.findElement(By.id("dt-nascimento")).sendKeys("21051996");
			assertEquals("21/05/1996", "21/05/1996");
		}

		// TESTES DE PREENCHIMENTO COLETIVO DOS CAMPOS
		@Test
		void PrechimentoCorretoDeTodosOsCampos() {
			driver.findElement(By.id("nome")).click();
			driver.findElement(By.id("nome")).sendKeys("Fulano da Silva");
			driver.findElement(By.id("cpf")).click();
			driver.findElement(By.id("cpf")).sendKeys("42693231833");
			driver.findElement(By.id("celular")).click();
			driver.findElement(By.id("celular")).sendKeys("11979776994");
			driver.findElement(By.id("dt-nascimento")).click();
			driver.findElement(By.id("dt-nascimento")).sendKeys("21051996");
			driver.findElement(By.id("btn-salvar")).click();

		}
}
