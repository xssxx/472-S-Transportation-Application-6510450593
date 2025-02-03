package ku.cs.transport_application.service.payment;

import ku.cs.transport_application.common.ProductType;

public class CalculatePriceService {
    public int calculateShipping(ProductType productType, int quantity) {
        int shippingCostPerItem;

        switch (productType) {
            case LIGHTWEIGHT:
                shippingCostPerItem = 50;
                break;
            case HEAVYWEIGHT:
                shippingCostPerItem = 150;
                break;
            case FRAGILE:
                shippingCostPerItem = 100; // ราคาขนส่งสำหรับสินค้าประเภทเปราะบาง
                break;
            case HAZARDOUS:
                shippingCostPerItem = 200; // ราคาขนส่งสำหรับสินค้าประเภทอันตราย
                break;
            case SPECIALTY:
                shippingCostPerItem = 300; // ราคาขนส่งสำหรับสินค้าประเภทพิเศษ
                break;
            default:
                throw new IllegalArgumentException("Unknown product type: " + productType);
        }

        return shippingCostPerItem * quantity;
    }
}
