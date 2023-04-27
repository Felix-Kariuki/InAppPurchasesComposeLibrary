# InAppPurchasesComposeLibrary
This is a Library to make in App purchases ans subscriptions integration to Jetpack Compose projects more easier

https://user-images.githubusercontent.com/61313608/215751864-2185d084-5c73-46c9-bda9-9da52c8e9442.mp4



## How to use

### 1. In your root `build.gradle` add: 

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

if you're using kotlin dsl use
```gradle
repositories {
    ...
    maven { url = uri("https://www.jitpack.io" ) }
}
```

### 2. Then include this dependency in your app level `build.gradle` 

```gradle
dependencies {
	   implementation 'com.github.Felix-Kariuki:InAppPurchasesComposeLibrary:<latest-version>'
}
```

### Implementation
 1. Create a new compose project 
 2. Publish the apk to playstore under closed testing or whichever you prefer
 3. On your playstore console on your newly created project under products, select in-app products and create a new product. on Product Id use `test_product` then on product details 
 add a product name and description and choose your prefered subscription amount. Once done remember to activate the subscription by clicking activate
 4. On your newly created or existing application on android studio, 
    * ### 1. initialize InAppPurchaseHelper class
    and pass the activity and product_id of your in app purchase as you named it on playstore
   
    
    ``` kotlin
    val billingPurchaseHelper = InAppPurchasesHelper(this,"test_product")
    billingPurchaseHelper.setUpBillingPurchases()
    ```
    ` NOTE `
    * ### 1.1 For Subscriptions ::
     
    ``` kotlin
    val billingPurchaseHelper = SubscriptionsHelper(this,"test_product_id")
    billingPurchaseHelper.setUpBillingPurchases()
    ```
    
    if you're implementing it on a compossable screen you can initiliaze it this way
    
    ``` kotlin
    val billingPurchaseHelper = InAppPurchasesHelper(LocalContext.current,"test_product_id")
    billingPurchaseHelper.setUpBillingPurchases()
    ```
    
    `for subscriptions`
    
     ``` kotlin
    val billingPurchaseHelper = SubscriptionsHelper(LocalContext.current,"test_product_id")
    billingPurchaseHelper.setUpBillingPurchases()
    ```
    
    you can also collect the purchse process, prduct name and purchase status by implementing this: 
    ``` kotlin
    val purchaseDone by billingPurchaseHelper.purchaseDone.collectAsState(false)
    val productName by billingPurchaseHelper.productName.collectAsState("")
    val purchaseStatus by billingPurchaseHelper.purchaseStatus.collectAsState("")
    ```
    * ### 2. on our purchases button on `onClick` initializePurchase 
    
    ``` kotlin
    billingPurchaseHelper.initializePurchase()
    ```
    if it's a one time purchase you can disable the button by passing `purchaseDone` as the enabled parameter to disable the button
    
    ``` kotlin
    Button(
        onClick = {
            billingPurchaseHelper.initializePurchase()
        },

        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        ),
        modifier = Modifier.wrapContentSize(),
        enabled = purchaseDone
    ) {
        Icon(
            Icons.Filled.More,
            contentDescription = "InApp Purchases",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            "In App Purchases",
            fontSize = 16.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
    ```
    
    ### Example 
    Chekout [This project](https://github.com/Felix-Kariuki/Yummy) to get an insight and more understanding of how to implement this or read this [Blog Post](https://felixkariuki.hashnode.dev/in-app-purchase-in-jetpack-compose)
    
    ## LICENSE
    ```
    
    Copyright 2023 Felix Kariuki

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    ```
