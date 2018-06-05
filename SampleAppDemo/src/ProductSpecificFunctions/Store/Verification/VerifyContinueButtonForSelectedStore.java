package ProductSpecificFunctions.Store.Verification;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import resources.ProductSpecificFunctions.Store.Verification.VerifyContinueButtonForSelectedStoreHelper;
import Framework.BSROException;
import Framework.CoreUtils;
import Framework.HtmlBrowser;
import Framework.HtmlText;
import KeyWordDriven.KeyWordDrivenConstants;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;

/**
 * Description : Functional Test Script
 * 
 * @author css89714
 */
public class VerifyContinueButtonForSelectedStore extends
		VerifyContinueButtonForSelectedStoreHelper {
	/**
	 * Script Name : <b>VerifyContinueButtonForSelectedStore</b> Generated :
	 * <b>Jul 18, 2013 1:54:25 PM</b> Description : Functional Test Script
	 * Original Host : WinNT Version 6.1 Build 7601 (S)
	 * 
	 * @since 2013/07/18
	 * @author css89714
	 * 
	 */

	public static void verify(String argStoreSelectionInfo)
			throws BSROException {

		String zip_state = "";

		String store_number = "";

		int totalStoresReturnedBySearchResults = 0;

		boolean isStoreIdentifiedByStoreNumber = false;

		try {

			String storeSelectionInfo = argStoreSelectionInfo;

			String[] storeSelectionParams = storeSelectionInfo.split(";");

			if (storeSelectionParams.length == 0) {

				throw new BSROException(
						"Required Input is provided to selectStore() function.");
			}

			for (int i = 0; i < storeSelectionParams.length; i++) {

				String currentParam = storeSelectionParams[i];

				if (currentParam.contains("Zip(or)State")) {

					zip_state = currentParam.substring(
							currentParam.indexOf("=") + 1,
							currentParam.length());
				} else if (currentParam.contains("StoreNumber")) {

					store_number = currentParam.substring(
							currentParam.indexOf("=") + 1,
							currentParam.length());

				} else {

					throw new BSROException(
							"Invalid parameter supplied to selectStore() function.");

				}

			}

			List<WebElement> eachStoreContainer = KeyWordDrivenConstants.webDriver
					.findElements(By.xpath("//*[@id=\"store-list\"]/ul/li"));

			totalStoresReturnedBySearchResults = eachStoreContainer.size();

			logInfo("Number Of Stores Identified = "
					+ totalStoresReturnedBySearchResults);

			if (totalStoresReturnedBySearchResults != BLFunctions.BLRulesData.StoreList.storeListCountServiceFunnel) {

				throw new BSROException(
						"totalStoresReturnedBySearchResults is not matched with BLRules Store list count.");

			}

			Iterator<WebElement> iterator = eachStoreContainer.iterator();

			while (iterator.hasNext()) {

				WebElement storeContainerObject = (WebElement) iterator.next();

//				storeContainerObject.click();

				List<WebElement> storeNumbersList = storeContainerObject
						.findElements(By.className("store-number"));

				Iterator<WebElement> iterator2 = storeNumbersList.iterator();

				int loopCount = 0;

				while (iterator2.hasNext()) {

					WebElement storeNumberObject = (WebElement) iterator2
							.next();

					String storeNumber = storeNumberObject.getText();

					System.out.println(storeNumber);

					if (storeNumber.equals(store_number)) {

						isStoreIdentifiedByStoreNumber = true;

						System.out.println("first loop break;");

						loopCount += 1;

						break;

					} else {

						loopCount += 1;

					}

				}

				if (isStoreIdentifiedByStoreNumber) {

					boolean isMyStoreGreenLogoExist = false;

					boolean isTemporarlyClosedLogoExist = false;

					boolean isContinueButtonExist = false;

					try {

						eachStoreContainer.get(loopCount).findElement(
								By.className("temporaryClosed"));

						isTemporarlyClosedLogoExist = true;

					} catch (Exception e) {
						// TODO: handle exception

						isTemporarlyClosedLogoExist = false;
					}

					try {
						eachStoreContainer.get(loopCount).findElement(
								By.className("my-store"));

						isMyStoreGreenLogoExist = true;

					} catch (Exception e) {
						// TODO: handle exception

						isMyStoreGreenLogoExist = false;
					}

					try {

						eachStoreContainer.get(loopCount).findElement(
								By.className("btn"));

						isContinueButtonExist = true;

					} catch (Exception e) {
						// TODO: handle exception

						isContinueButtonExist = false;
					}

					if (true == isContinueButtonExist
							&& true == isMyStoreGreenLogoExist
							&& false == isTemporarlyClosedLogoExist) {

						logInfo("Continue button exist for already selected store.");

						break;

					} else {

						throw new BSROException(
								"Continue button does not exist for already selected store.");

					}

				}

			}

			if (false == isStoreIdentifiedByStoreNumber) {

				throw new BSROException(
						"Store with given store number is not identified.");

			}

			HtmlBrowser.waitForLoad();

		} catch (Exception e) {
			// TODO: handle exception

			logError("VerifyContinueButtonForSelectedStore.verify() function failed.");

			throw new BSROException(e);

		}

	}

}
