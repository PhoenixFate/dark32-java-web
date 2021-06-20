var CART = {
	itemNumChange : function(){
		$(".increment").click(function(){//＋
			var _thisInput = $(this).siblings("input");
			_thisInput.val(eval(_thisInput.val()) + 1);
			var itemId=_thisInput.attr("itemId");
			console.log("itemId: "+itemId);
			var moneyId="#"+itemId+"_total_price";
			console.log("money: "+moneyId);
			var price=_thisInput.attr("itemPrice");
			console.log("price: "+price);
			var total= (eval(_thisInput.attr("itemPrice")) * 10000 * eval(_thisInput.val())) / 10000;
			console.log("total: "+total);
			$(moneyId).html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
				prefix: '¥',
				thousandsSeparator: ',',
				centsLimit: 2
			});

			$.post("../cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".do",function(data){
				CART.refreshAllTotalPrice();
			});
		});
		$(".decrement").click(function(){//-
			var _thisInput = $(this).siblings("input");
			if(eval(_thisInput.val()) == 1){
				return ;
			}
			_thisInput.val(eval(_thisInput.val()) - 1);

			var itemId=_thisInput.attr("itemId");
			console.log("itemId: "+itemId);
			var moneyId="#"+itemId+"_total_price";
			console.log("money: "+moneyId);
			var price=_thisInput.attr("itemPrice");
			console.log("price: "+price);
			var total= (eval(_thisInput.attr("itemPrice")) * 10000 * eval(_thisInput.val())) / 10000;
			console.log("total: "+total);
			$(moneyId).html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
				prefix: '¥',
				thousandsSeparator: ',',
				centsLimit: 2
			});

			$.post("../cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val() + ".do",function(data){
				CART.refreshAllTotalPrice();
			});
		});
		/*$(".itemnum").change(function(){
			var _thisInput = $(this);
			$.post("/service/cart/update/num/"+_thisInput.attr("itemId")+"/"+_thisInput.val(),function(data){
				CART.refreshTotalPrice();
			});
		});*/
	},




	refreshAllTotalPrice : function(){ //重新计算总价  小计也要重新计算
		var total = 0;
		$(".itemnum").each(function(i,e){
			var _this = $(e);
			total += (eval(_this.attr("itemPrice")) * 10000 * eval(_this.val())) / 10000;
		});
		$("#allMoney2").html(new Number(total/100).toFixed(2)).priceFormat({ //价格格式化插件
			 prefix: '¥',
			 thousandsSeparator: ',',
			 centsLimit: 2
		});
	}
};




$(function(){
	CART.itemNumChange();
});