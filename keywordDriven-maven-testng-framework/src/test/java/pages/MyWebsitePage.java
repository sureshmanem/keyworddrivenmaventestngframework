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
package pages;

import org.openqa.selenium.By;

public class MyWebsitePage {

    // Header
    public static final By lblHeader = By.xpath("//header//h2/a");

    // Download CV link
    public static final By lnkDownloadCV = By.xpath("//a[contains(text(),'Download CV')]");

    // Navigation links
    public static final By lnkProfileSummary = By.xpath("/html/body/section[2]/div/div/div/div/h2");
    public static final By lnkAbout = By.xpath("//nav//a[contains(text(),'About')]");
    public static final By lnkSkills = By.xpath("//nav//a[contains(text(),'Skills')]");
    public static final By lnkProjects = By.xpath("//nav//a[contains(text(),'Projects')]");
    public static final By lnkContact = By.xpath("//nav//a[contains(text(),'Contact')]");

    // About section
    public static final By sectionAbout = By.id("about");
    public static final By lblAboutTitle = By.xpath("//section[@id='about']//h2");

    // Skills section
    public static final By sectionSkills = By.id("skills");
    public static final By lblSkillsTitle = By.xpath("//section[@id='skills']//h2");

    // Projects section
    public static final By sectionProjects = By.id("projects");
    public static final By lblProjectsTitle = By.xpath("//section[@id='projects']//h2");

    // Contact section
    public static final By sectionContact = By.id("contact");
    public static final By lblContactTitle = By.xpath("//section[@id='contact']//h2");

    // Social links (example: LinkedIn, GitHub)
    public static final By lnkLinkedIn = By.xpath("//a[contains(@href,'linkedin.com')]");
    public static final By lnkGitHub = By.xpath("//a[contains(@href,'github.com')]");

}
