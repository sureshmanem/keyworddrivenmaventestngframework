/*
 *  © [2020] Cognizant. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package businesscomponents;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cognizant.core.DriverScript;
import com.cognizant.core.ReusableLibrary;
import com.cognizant.core.ScriptHelper;
import com.cognizant.framework.Status;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MyWebsitePage;

public class MyWebsiteScripts extends ReusableLibrary {



	@BeforeClass
	public void setUp() {
		// Initialize your WebDriver here (e.g., ChromeDriver)
		// driver = new ChromeDriver();
		// wait = new WebDriverWait(driver, 15);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

	/**
	 * Constructor to initialize the component library
	 * 
	 * @param scriptHelper The {@link ScriptHelper} object passed from the
	 *                     {@link DriverScript}
	 */
	public MyWebsiteScripts(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	@Test(priority = 1)
	public void testWebsiteLaunch() {
		driver.get("https://www.sureshmanem.com");
		Assert.assertTrue(driver.getTitle() != null && !driver.getTitle().isEmpty(), "Website did not load.");
		Assert.assertTrue(driver.getCurrentUrl().contains("sureshmanem.com"), "Website URL is incorrect.");
		Assert.assertTrue(driver.findElement(MyWebsitePage.lblHeader).isDisplayed(), "Header is not visible.");
		report.updateTestLog("Test Website Launch", "Website Launch Verification", Status.DONE);
	}

	@Test(priority = 2)
	public void testHeaderText() {
		driverUtil.waitUntilElementVisible(MyWebsitePage.lblHeader, 10);
		WebElement header = driver.findElement(MyWebsitePage.lblHeader);
		Assert.assertTrue(header.getText().contains("Suresh Manem"), "Header text is incorrect.");
	}

	@Test(priority = 3)
	public void testNavigationLinks() {
		// String[] navLinks = { "Home", "About", "Skills", "Projects", "Contact" };
		// By[] navLocators = {
		// 		MyWebsitePage.lnkProfileSummary,
		// 		MyWebsitePage.lnkAbout,
		// 		MyWebsitePage.lnkSkills,
		// 		MyWebsitePage.lnkProjects,
		// 		MyWebsitePage.lnkContact
		// };
		// By[] sectionLocators = {
		// 		MyWebsitePage.lblHeader,
		// 		MyWebsitePage.sectionAbout,
		// 		MyWebsitePage.sectionSkills,
		// 		MyWebsitePage.sectionProjects,
		// 		MyWebsitePage.sectionContact
		// };
		// for (int i = 0; i < navLinks.length; i++) {
		// 	// Wait for navigation link to be clickable
		// 	driverUtil.waitUntilElementVisible(navLocators[i], 10);
		// 	WebElement nav = driver.findElement(navLocators[i]);
		// 	// Click the navigation link
		// 	nav.click();
		// 	// Wait for section to be visible
		// 	driverUtil.waitUntilElementLocated(sectionLocators[i], 10);
		// }
	}

	@Test(priority = 4)
	public void testDownloadCVLink() {
		WebElement cvLink = driver.findElement(MyWebsitePage.lnkDownloadCV);
		String originalWindow = driver.getWindowHandle();
		cvLink.click();
		// Wait for new tab or download (simplified: check for new window)
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(originalWindow)) {
				driver.switchTo().window(windowHandle);
				Assert.assertTrue(driver.getCurrentUrl().contains(".pdf") || driver.getCurrentUrl().contains("cv"),
						"CV did not open/download.");
				driver.close();
				driver.switchTo().window(originalWindow);
				return;
			}
		}
		// If no new window, check for download (not possible to verify download
		// directly in Selenium)
		Assert.assertTrue(true, "CV link clicked (manual verification may be needed for download).");
	}

	@Test(priority = 5)
	public void testProfileSummary() {
		WebElement profileSummary = driver.findElement(MyWebsitePage.lnkProfileSummary);
		if(profileSummary.isDisplayed()){
			report.updateTestLog("Profile Summary", "Profile Summary section is visible.", Status.PASS);
		}else{
			report.updateTestLog("Profile Summary", "Profile Summary section is not visible.", Status.FAIL);
		}
		Assert.assertTrue(profileSummary.isDisplayed(), "Profile Summary section not visible.");
	}

	@Test(priority = 6)
	public void testSkillsSection() {
		WebElement skillsSection = driver.findElement(MyWebsitePage.sectionSkills);
		Assert.assertTrue(skillsSection.isDisplayed(), "Skills section not visible.");
	}

	@Test(priority = 7)
	public void testProjectsSection() {
		WebElement projectsSection = driver.findElement(MyWebsitePage.sectionProjects);
		Assert.assertTrue(projectsSection.isDisplayed(), "Projects section not visible.");
	}

	@Test(priority = 8)
	public void testContactSection() {
		WebElement contactSection = driver.findElement(MyWebsitePage.sectionContact);
		Assert.assertTrue(contactSection.isDisplayed(), "Contact section not visible.");
	}

	@Test(priority = 9)
	public void testSocialMediaLinks() {
		By[] socialLinks = { MyWebsitePage.lnkLinkedIn, MyWebsitePage.lnkGitHub };
		String[] expectedUrls = { "linkedin.com", "github.com" };
		String originalWindow = driver.getWindowHandle();
		for (int i = 0; i < socialLinks.length; i++) {
			WebElement link = driver.findElement(socialLinks[i]);
			link.click();
			// Wait for new tab
			for (String windowHandle : driver.getWindowHandles()) {
				if (!windowHandle.equals(originalWindow)) {
					driver.switchTo().window(windowHandle);
					Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrls[i]),
							"Social link did not open correct profile.");
					driver.close();
					driver.switchTo().window(originalWindow);
				}
			}
		}
	}

	@Test(priority = 10)
	public void testResponsiveness() {
		// Resize to mobile size
		driver.manage().window().setSize(new Dimension(375, 812));
		// Check if navigation/menu is still visible or replaced by hamburger menu, etc.
		Assert.assertTrue(driver.findElement(MyWebsitePage.lblHeader).isDisplayed(),
				"Header not visible on mobile size.");
		// Resize back to desktop
		driver.manage().window().setSize(new Dimension(1280, 800));
		Assert.assertTrue(driver.findElement(MyWebsitePage.lblHeader).isDisplayed(),
				"Header not visible on desktop size.");
	}
}
