package Interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IXpathMaker  {
     By LocatorBy_ID(String ID);
     By LocatorBy_Name(String Name);
     By LocatorBy_Xpath(String Xpath);
     By LocatorBy_CssSelector(String CssSelector);
     By LocatorBy_ClassName(String ClassName);
     By LocatorBy_VisibleText(String VisibleText);
}
