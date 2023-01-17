import React from 'react';
import CourierNavbar from "./CourierNavbar";
import CourierOrderList from "./CourierOrderList";
import CourierAssignedOrdersList from "./CourierAssignedOrdersList";
import CourierAvailableOrderTable from "./CourierAvailableOrderTable";

const DisplayMode = {
    OrderList: 0,
    AssignedOrderList: 1
}

class CourierPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {mode: DisplayMode.OrderList};
    }

    displayOrderList = () =>  {
        this.setState({mode: DisplayMode.OrderList});
    }

    displayAssignedOrderList = () => {
        this.setState({mode: DisplayMode.AssignedOrderList});
    }

    getPageContent() {
        switch (this.state.mode) {
            case (DisplayMode.OrderList): {
                return <CourierAvailableOrderTable/>;
            }

            case (DisplayMode.AssignedOrderList): {
                return <CourierAssignedOrdersList/>;
            }

            default:
                return <CourierOrderList/>;

        }
    }

    render() {
        return (
            <div className="with-custom-webkit-scrollbars">
                <div className="page-wrapper with-navbar with-sidebar">
                    <div className="sticky-alerts"/>

                    <nav className="navbar">
                        <CourierNavbar name="Egor Courier"/>
                    </nav>

                    <div className="sidebar">
                        <div className="mt-20">
                            <a href="#" className="sidebar-link sidebar-link-with-icon pt-20" onClick={this.displayOrderList}>
                                Orders to pick up
                            </a>
                            <a href="#" className="sidebar-link sidebar-link-with-icon pt-20" onClick={this.displayAssignedOrderList}>Assigned orders</a>
                        </div>
                    </div>

                    <div className="content-wrapper">
                        {this.getPageContent()}
                    </div>
                </div>
            </div>
        );
    }
}

export default CourierPage;