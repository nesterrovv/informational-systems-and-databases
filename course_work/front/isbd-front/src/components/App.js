import React, {Component} from "react";
import CustomerPage from "./customer/CustomerPage";
import EntryPage from "./common/EntryPage";
import CourierOrderList from "./courier/CourierOrderList";
import CourierPage from "./courier/CourierPage";
import {useSelector} from "react-redux";
require("halfmoon/css/halfmoon-variables.min.css");
// Import JS library
const halfmoon = require("halfmoon");

class App extends Component {

    componentDidMount() {
        // halfmoon.onDOMContentLoaded();
    }

    render() {
        return (
            <React.Fragment>
                {/*<CustomerPage/>*/}
                <EntryPage/>
                {/*<CourierPage/>*/}
            </React.Fragment>
        );
    }
}
export default App