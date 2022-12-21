import React, {Component} from 'react';
import CustomerOrder from "./CustomerOrder";

class CustomerOrderList extends React.Component {

    componentDidMount() {
        //TODO: get from server list of orders
    }

    render() {
        return (
            <div>
                <CustomerOrder order_status={'WAITING'} departure="Capital Knot City"
                               destination="South Knot City"
                               description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "/>
                <CustomerOrder order_status={'LOST'} departure="Capital Knot City" destination="South Knot City"
                               description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "/>
                <CustomerOrder order_status={'DESTROYED'} departure="Capital Knot City"
                               destination="South Knot City" description=""/>
                <CustomerOrder order_status={'DELIVERING'} departure="Capital Knot City"
                               destination="South Knot City" description=""/>
                <CustomerOrder order_status={'DELIVERED'} departure="Capital Knot City"
                               destination="South Knot City" description=""/>
            </div>
        );
    }
}

export default CustomerOrderList;