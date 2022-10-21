package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait ;
	Actions action ;
	
	@FindBy(xpath = "//input")
	WebElement Search_Text ; 
	
	@FindBy(xpath = "//a[contains(text(),'News')]")
	WebElement Click_News ;
	
	@FindBy(xpath = "//img[contains(@id , 'dimg')]/ancestor::div/span[not(starts-with(text(),'.'))]")
	List <WebElement> News_agencies ; 
	
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions ( driver );
		wait = new WebDriverWait ( driver , Duration.ofSeconds(30) ) ;
	}
	
	public void search_Google (String searchKeyword)
	{
		wait.until(ExpectedConditions.visibilityOf(Search_Text)) ;
		Search_Text.click();
		Search_Text.sendKeys(searchKeyword);
		action.sendKeys(Keys.ENTER).build().perform();
		
	}
	
	
	public void Navigate_to_News_Tab_and_Store_Links () 
	{
		wait.until(ExpectedConditions.visibilityOf(Click_News)) ;
		Click_News.click();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(News_agencies)) ;
		int SIZE = News_agencies.size();
		System.out.println("SIZE : " + SIZE);
		ArrayList<String> list_of_news_agenciesList = new  ArrayList<String>() ;
		ArrayList<String> Unique_News_Agencies = new  ArrayList<String>() ;
		
		for( int  i = 0 ; i< SIZE ; i++)
		{
		   list_of_news_agenciesList.add ( News_agencies.get(i).getText() );
		   
		}
		
		for( int  i = 0 ; i< SIZE ; i++)
		{
			
		   if ( ! HomePage.validate_if_text_Present_in_List(list_of_news_agenciesList.get(i), list_of_news_agenciesList ) )
		   {
			   Unique_News_Agencies.add( list_of_news_agenciesList.get(i) );
			   
		   }
		}
		
		if(Unique_News_Agencies.size()<3)
			System.out.println( " Missing Leading News Agencies");
		else
		{
			System.out.println("Names of News Agencies");
			for( int  i = 0 ; i< Unique_News_Agencies.size() ; i++)
			{
			  System.out.println( (i+1) + ".   " + Unique_News_Agencies.get(i));
			}
		}
			
		
	}
	
	public static boolean validate_if_text_Present_in_List(String text , ArrayList<String> arr_List  )
	{
		int j= 0 , count = 0 ;
		boolean flag = false ;
		 do
		 {
			 if( text.contentEquals(arr_List.get(j)) == true )
			 {
				 count++ ;
				 if(count > 1 )
					{
						break;
					}
			 }
			
			j++; 
		 }while ( j < arr_List.size()  ) ;
		 if(count > 1 )
			{
				flag = true ;
			}
		 return flag ;
		
	}

}
