package com.smartwavettn.horoscope.ui.inapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.collect.ImmutableList
import com.smartwavettn.horoscope.R
import com.smartwavettn.horoscope.base.local.Preferences

class PurchaseActivity : AppCompatActivity(),
    PurchaseAdapter.OnClickListener {
    private var adapter: PurchaseAdapter? = null
    private var billingClient: BillingClient? = null
    private var handler: Handler? = null
    private var productDetailsList: MutableList<ProductDetails>? = null
    private var onPurchaseResponse: OnPurchaseResponse? = null
    private var listData: RecyclerView? = null
    private var imgBack: ImageView? = null
    private var llNoData: LinearLayout? = null

    private lateinit var pref: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        pref = Preferences.getInstance(this)
        initViews()
        imgBack!!.setOnClickListener { onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        billingClient!!.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP)
                .build()
        ) { billingResult: BillingResult, list: List<Purchase> ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                for (purchase in list) {
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
                        verifyInAppPurchaseApplication(purchase)
                    }
                }
            }
        }
    }

    private fun initViews() {
        listData = findViewById(R.id.listData)
        imgBack = findViewById(R.id.imvBack)
        adapter =
            PurchaseAdapter()
        llNoData = findViewById(R.id.LllNoData)
        listData?.setHasFixedSize(true)
        listData?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listData?.adapter = adapter
        adapter!!.setOnClickListener(this)
        productDetailsList = ArrayList()
        handler = Handler()
        billingClient = BillingClient.newBuilder(this).enablePendingPurchases()
            .setListener { billingResult: BillingResult?, list: List<Purchase?>? -> }
            .build()
        establishConnectioncApplication()
    }

    fun establishConnectioncApplication() {
        billingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    showProducts()
                }
            }

            override fun onBillingServiceDisconnected() {
                establishConnectioncApplication()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    fun showProducts() {
        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(inAppProductList)
            .build()
        billingClient!!.queryProductDetailsAsync(
            params
        ) { billingResult: BillingResult?, prodDetailsList: List<ProductDetails> ->
            productDetailsList!!.clear()
            handler!!.postDelayed({
                productDetailsList!!.addAll(prodDetailsList)
                adapter!!.setData(this, productDetailsList)
                if (prodDetailsList.isEmpty()){
                    llNoData?.visibility = View.VISIBLE
                    listData?.visibility = View.GONE
                    Toast.makeText(
                        this@PurchaseActivity,
                        "prodDetailsList, size = 0",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    llNoData?.visibility = View.GONE
                    listData?.visibility = View.VISIBLE
                }
            }, 2000)
        }
    }


    private val inAppProductList: ImmutableList<QueryProductDetailsParams.Product>
        private get() = ImmutableList.of(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_1)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),//Product 1
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_2)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),    //Product 2
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_3)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 3
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_4)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 4
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_5)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 5
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_6)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),  //Product 6
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_7)
                .setProductType(BillingClient.ProductType.INAPP)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(Constant.KEY_NOTE_8)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )

    fun verifyInAppPurchaseApplication(purchases: Purchase) {
        val acknowledgePurchaseParams = AcknowledgePurchaseParams
            .newBuilder()
            .setPurchaseToken(purchases.purchaseToken)
            .build()
        billingClient!!.acknowledgePurchase(acknowledgePurchaseParams) { billingResult: BillingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                val proId = purchases.products[0]
                val quantity = purchases.quantity
                setPurchaseResponseApplication(object : OnPurchaseResponse {
                    override fun onResponse(proId: String?, quantity: Int) {
                        proId?.let {
                            setupResultApplication(
                                it,
                                quantity
                            )
                        }
                    }
                })
                onPurchaseResponse!!.onResponse(proId, quantity)
                allowMultiplePurchasesApplication(purchases)
            }
        }
    }

    private fun allowMultiplePurchasesApplication(purchase: Purchase) {
        val consumeParams = ConsumeParams
            .newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()
        billingClient!!.consumeAsync(consumeParams) { billingResult, s ->
            Toast.makeText(
                this@PurchaseActivity,
                " Resume item ",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onClickItem(item: ProductDetails) {
        launchPurchaseFlowApplication(item)
    }

    private fun launchPurchaseFlowApplication(productDetails: ProductDetails) {
        val productDetailsParamsList = ImmutableList.of(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(productDetails)
                .build()
        )
        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()
        billingClient!!.launchBillingFlow(this, billingFlowParams)
    }

    private fun setupResultApplication(proId: String, quantity: Int) {
        val intent = Intent()
        val totalCoin = pref.getValueCoin()
        val remainCoin = totalCoin
        pref.setValueCoin(remainCoin)
        setResult(RESULT_OK, intent)
        runOnUiThread { onBackPressed() }
    }

    private fun getCoinFromKeyApplication(coinId: String): Int {
        return when (coinId) {
            Constant.KEY_NOTE_1 -> 1
            Constant.KEY_NOTE_2 -> 5
            Constant.KEY_NOTE_3 -> 30
            Constant.KEY_NOTE_4 -> 50
            Constant.KEY_NOTE_5 -> 80
            Constant.KEY_NOTE_6 -> 120
            Constant.KEY_NOTE_7 -> 150
            Constant.KEY_NOTE_8 -> 250
            else -> 0
        }
    }

    internal interface OnPurchaseResponse {
        fun onResponse(proId: String?, quantity: Int)
    }

    private fun setPurchaseResponseApplication(onPurchaseResponse: OnPurchaseResponse) {
        this.onPurchaseResponse = onPurchaseResponse
    }

}