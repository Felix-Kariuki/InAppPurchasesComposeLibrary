package com.flexcode.inapppurchasescomposelibrary

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.flexcode.inapppurchasescompose.InAppPurchasesHelper
import com.flexcode.inapppurchasescomposelibrary.ui.theme.InAppPurchasesComposeLibraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InAppPurchasesComposeLibraryTheme {
                // A surface container using the 'background' color from the theme

                val billingPurchasesHelper = InAppPurchasesHelper(this)
                billingPurchasesHelper.setUpBillingPurchases()


                val purchaseDone by billingPurchasesHelper.purchaseDone.collectAsState(false)
                val productName by billingPurchasesHelper.productName.collectAsState("")
                val purchaseStatus by billingPurchasesHelper.purchaseStatus.collectAsState("")

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    Text(text = "Hello",
                    modifier = Modifier.clickable {
                        billingPurchasesHelper.initializePurchase()
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InAppPurchasesComposeLibraryTheme {
        Greeting("Android")
    }
}