// This is a simple *viewmodel* - JavaScript that defines the data and behavior of your UI
var baseUrl = '';


function AppViewModel() {
    var self = this;

    this.products = ko.observable();
    this.cart = ko.observable();
    this.commerceItems = ko.observable([]);

    this.addToCart = function (item) {

        $.ajax({
            type: "POST",
            url: baseUrl + "/shoppingcart/items",
            data: {product_id: item.id, quantity: 1},
            success: function (response) {
                self.updateCart();
            }
        });
    };

    this.updateCart = function () {
        $.get(baseUrl + '/shoppingcart', function (response) {
            self.cart(response);
            self.commerceItems(response.commerceItems);
        });
    };

    this.formatCurrency = function (value) {
        return "$" + value.toFixed(2);
    };

    this.removeItem = function (item) {
        $.ajax({
            type: "DELETE",
            url: baseUrl + '/shoppingcart/items/' + item.id,
            data: {product_id: item.id, quantity: 1},
            success: function (response) {
                self.updateCart();
            }
        });
    };

    this.init = function () {
        $.get(baseUrl + '/products', function (response) {
            self.products(response);
        });
        this.updateCart();
    };

    this.init();
}

// Activates knockout.js
ko.applyBindings(new AppViewModel());