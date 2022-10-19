package com.teamx.zeus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teamx.zeus.baseclasses.BaseViewModel
import com.teamx.zeus.data.models.ProductModel


/**
 * Shared View Model class for sharing data between fragments
 */
class SharedViewModel : BaseViewModel() {

    val clickOnContinueBtn: MutableLiveData<Boolean>? = null

    val addToCartProduct = MutableLiveData<ArrayList<ProductModel>>()

    private val _productBySlug = MutableLiveData<String>()
    val productBySlug: LiveData<String>
        get() = _productBySlug

    fun setProductBySlug(_productBySlug: String) {
        this._productBySlug.value = _productBySlug
    }

    private val _shopBySlug = MutableLiveData<String>()
    val shopBySlug: LiveData<String>
        get() = _shopBySlug

    fun setShopBySlug(_shopBySlug: String) {
        this._shopBySlug.value = _shopBySlug
    }


    private val _shopById = MutableLiveData<String>()
    val shopById: LiveData<String>
        get() = _shopById

    fun setShopById(_shopById: String) {
        this._shopById.value = _shopById
    }

    fun addProduct(model: ProductModel) {
        val list = addToCartProduct.value;
        if (list != null) {
            list.add(model);
            addToCartProduct.postValue(list!!)
        } else {
            val newList = ArrayList<ProductModel>();
            newList.add(model)
            addToCartProduct.postValue(newList)
        }
    }

    private val _productId = MutableLiveData<String>()
    val productId: LiveData<String>
        get() = _productId

    fun setProductId(_productId: String) {
        this._productId.value = _productId
    }

    fun removeProduct(deleteModel: ProductModel) {
        val list = addToCartProduct.value
        if (list != null && list.size > 0) {
            var index = 0;
            var found = false
            while (!found && index < list.size) {
                if (list.get(index).id == deleteModel.id) {
                    list.removeAt(index)
                    found = true
                }
                index++;
            }
            if (list != null && list.size > 0) {
                addToCartProduct.postValue(list!!)
            } else {
                addToCartProduct.postValue(ArrayList())
            }
        }
    }
}