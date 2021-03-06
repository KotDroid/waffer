
package com.waffer.webapp.Model.order_model.get_tax;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.waffer.webapp.Model.order_model.OrderShippingMethod;
import com.waffer.webapp.Model.order_model.UserBilling;
import com.waffer.webapp.Model.order_model.UserShipping;

import java.util.List;

public class GetTax {

    @SerializedName("billing_info")
    @Expose
    private UserBilling billingInfo;
    @SerializedName("shipping_info")
    @Expose
    private UserShipping shippingInfo;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("shipping_ids")
    @Expose
    private List<OrderShippingMethod> shippingIds = null;

    public UserBilling getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(UserBilling billingInfo) {
        this.billingInfo = billingInfo;
    }

    public UserShipping getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(UserShipping shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<OrderShippingMethod> getShippingIds() {
        return shippingIds;
    }

    public void setShippingIds(List<OrderShippingMethod> shippingIds) {
        this.shippingIds = shippingIds;
    }

}
