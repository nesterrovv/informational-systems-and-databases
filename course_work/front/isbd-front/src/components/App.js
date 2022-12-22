import React, {Component} from "react";
import CustomerPage from "./customer/CustomerPage";
import EntryPage from "./common/EntryPage";
require("halfmoon/css/halfmoon-variables.min.css");
// Import JS library
const halfmoon = require("halfmoon");

class App extends Component {

    componentDidMount() {
        // halfmoon.onDOMContentLoaded();
    }

    render() {
        return (
            // <React.Fragment>
            //      <CourierOrder order_status={'WAITING'} departure="Capital Knot City" destination="South Knot City" description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sagittis sem justo, sit amet vulputate leo malesuada at. Morbi eros tellus, venenatis eget imperdiet ut, fringilla et ligula. Sed a orci venenatis, iaculis massa sit amet, facilisis nisi. Etiam aliquet, tortor eget pulvinar eleifend, magna nulla mattis lectus, eget ultrices.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sagittis sem justo, sit amet vulputate leo malesuada at. Morbi eros tellus, venenatis eget imperdiet ut, fringilla et ligula. Sed a orci venenatis, iaculis massa sit amet, facilisis nisi. Etiam aliquet, tortor eget pulvinar eleifend, magna nulla mattis lectus, eget ultrices."/>
            //      <CourierOrder order_status={'LOST'} departure="Capital Knot City" destination="South Knot City" description="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sagittis sem justo, sit amet vulputate leo malesuada at. Morbi eros tellus, venenatis eget imperdiet ut, fringilla et ligula. Sed a orci venenatis, iaculis massa sit amet, facilisis nisi. Etiam aliquet, tortor eget pulvinar eleifend, magna nulla mattis lectus, eget ultrices."/>
            //      <CourierOrder order_status={'DESTROYED'} departure="Capital Knot City" destination="South Knot City" description=""/>
            //      <CourierOrder order_status={'DELIVERING'} departure="Capital Knot City" destination="South Knot City" description=""/>
            //      <CourierOrder order_status={'DELIVERED'} departure="Capital Knot City" destination="South Knot City" description=""/>
            //  </React.Fragment>
            <React.Fragment>
                {/*<CustomerPage/>*/}
                <EntryPage/>
            </React.Fragment>
        );
    }
}
export default App