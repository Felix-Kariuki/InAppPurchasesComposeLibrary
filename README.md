# InAppPurchasesComposeLibrary
This is a Library to make in App purchases integration to Jetpack Compose projects more easier

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
	   implementation 'com.github.Felix-Kariuki:InAppUrchasesComposeLibrary:0.1.1'
}
```

### Implementation
 1. Create a new compose project 
 2. Publish the apk to playstore under closed testing or whichever you prefer
 3. On your playstore console on your newly created project under products, select in-app products and create a new product. on Product Id use `test_product` then on product details 
 add a product name and description and choose your prefered subscription amount. Once done remember to activate the subscription by clicking activate
 4. On your newly created or existing application on android studio, 
    * ### 1. initialize InAppPurchaseHelper class
    
    ```
    val billingPurchaseHelper = InAppPurchasesHelper(this)
    billingPurchaseHelper.setUpBillingPurchases()
    ```
    if you're implementing it on a compossable screen you can initiliaze it this way
    ```
    val billingPurchaseHelper = InAppPurchasesHelper(LocalContext.current as Activity)
    billingPurchaseHelper.setUpBillingPurchases()
    ```
    
    you can also collect the purchse process, prduct name and purchase status by implementing this: 
    ```
    val purchaseDone by billingPurchaseHelper.purchaseDone.collectAsState(false)
    val productName by billingPurchaseHelper.productName.collectAsState("")
    val purchaseStatus by billingPurchaseHelper.purchaseStatus.collectAsState("")
    ```
    * ### 2. on our purchases button on `onClick` initializePurchase 
    
    ```
    billingPurchaseHelper.initializePurchase()
    ```
    if it's a one time purchase you can disable the button by passing `purchaseDone` as the enabled parameter to disable the button
    
    ```
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
    Chekout [This project](https://github.com/Felix-Kariuki/Yummy) to get an insight and more understanding of how to implement this or read this [Blog Post]()
