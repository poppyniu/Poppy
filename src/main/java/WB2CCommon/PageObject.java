package WB2CCommon;

import org.openqa.selenium.WebDriver;


public class PageObject {

    public WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }
    
//    public void logout() throws Exception{
//    	SideTopNavBar sideTopNavBar = new SideTopNavBar(driver);
//    	sideTopNavBar.clickMeIcon();
//    	sideTopNavBar.clickSignOut();
//	}
//	
//    /**
//     * Click "Settings -> Site Contents" on top right corner
//     * 
//     * @return
//     * @throws Exception 
//     */
//	public SiteContentsPage clickTopRightSiteContentsSubMenu() throws Exception {
//		SideTopNavBar sideTopNavBar = new SideTopNavBar(driver);
//		sideTopNavBar.clickSettings();
//		sideTopNavBar.clickSiteContentsSubMenu();
//        return (new SiteContentsPage(driver));
//    }
//	
//	/**
//	 * Go to mail page from top left menu bar
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public MailPage goToMailPage() throws Exception {
//		SideTopNavBar sideTopNavBar = new SideTopNavBar(driver);
//		sideTopNavBar.clickMainLinkOnTopLeft();
//		sideTopNavBar.clickMailLinkOnPopupMenu();
//        return (new MailPage(driver));
//    }
//	
//	/**
//	 * Go to sub site's home page
//	 * 
//	 * @param subsiteName
//	 * @return
//	 * @throws Exception
//	 */
//	public HomePage goToSubsite(String subsiteName) throws Exception{
//		SideTopNavBar sideTopNavBar = new SideTopNavBar(driver);
//		SiteContentsPage siteContentsPage = sideTopNavBar.clickSiteContents();
//		siteContentsPage.clickSubsitesLink(subsiteName);
//		DriverUtil.wait(driver, 3);
//		System.out.println(driver.getCurrentUrl());
//		int n = 0;
//		while(!driver.getCurrentUrl().contains(subsiteName) && n <4){
//			DriverUtil.wait(driver, 5);
//			n++;
//		}
//		return (new HomePage(driver));
//	}
//	
//	/**
//	 * Click "Site Contents" on left menu and then click a content item on Site Contents page 
//	 * 
//	 * @param contentTitle Content item title
//	 * @throws Exception 
//	 */
//	public void goToOtherContentPage(String contentTitle) throws Exception {
//		SideTopNavBar sideTopNavBar = new SideTopNavBar(driver);
//		SiteContentsPage siteContentsPage = sideTopNavBar.clickSiteContents();
//		siteContentsPage.clickContentItem(contentTitle);
//		DriverUtil.wait(driver, 3);
//	}
//	
//	/**
//	 * Click Home link on left navigator to open home page
//	 * @throws Exception
//	 */
//	public void goToHomePage() throws Exception {
//		SideTopNavBar sideTopNavBar = new SideTopNavBar(driver);
//		sideTopNavBar.clickHome();
//		DriverUtil.wait(driver, 3);
//	}
}

