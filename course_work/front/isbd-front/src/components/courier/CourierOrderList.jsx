import React from 'react';
import CourierOrder from "./CourierOrder";

const CourierOrderList = () => {
    return (
        <div>
            <CourierOrder order_status={'WAITING'} departure="Capital Knot City"
                           destination="South Knot City"
                           description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "/>
            <CourierOrder order_status={'LOST'} departure="Capital Knot City" destination="South Knot City"
                           description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "/>
            <CourierOrder order_status={'DESTROYED'} departure="Capital Knot City"
                           destination="South Knot City" description=""/>
            <CourierOrder order_status={'DELIVERING'} departure="Capital Knot City"
                           destination="South Knot City" description=""/>
            <CourierOrder order_status={'DELIVERED'} departure="Capital Knot City"
                           destination="South Knot City" description=""/>
        </div>
    );
};

export default CourierOrderList;