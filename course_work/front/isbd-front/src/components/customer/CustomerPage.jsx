import React, {Component} from 'react';
import CustomerOrderList from "./CustomerOrderList";
import CustomerNavbar from "./CustomerNavbar";
import NewOrderForm from "./NewOrderForm";

const DisplayMode = {
    NewOrder: 0,
    OrderList: 1
}

class CustomerPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {mode: DisplayMode.NewOrder};
    }

    getPageContent() {
        switch (this.state.mode) {
            case (DisplayMode.OrderList): {
                return <CustomerOrderList/>;
            }
            case (DisplayMode.NewOrder): {
                return <NewOrderForm/>;
            }
            default:
                return <CustomerOrderList/>;

        }
    }

    displayOrderList = () =>  {
        this.setState({mode: DisplayMode.OrderList});
        console.log(this.state);
    }

    displayNewOrderForm = () => {
        // let newState = {...this.state.mode};
        // newState.mode = DisplayMode.NewOrder;
        // this.setState(newState);
        this.setState({mode: DisplayMode.NewOrder});
        console.log(this.state);
    }
    render() {
        return (
            <div className="with-custom-webkit-scrollbars">
                <div className="page-wrapper with-navbar with-sidebar">

                    <nav className="navbar">
                        <CustomerNavbar name="Egor Samurai"/>
                    </nav>

                    <div className="sidebar">
                        <div className="mt-20">
                            <a href="#" className="sidebar-link sidebar-link-with-icon pt-20" onClick={this.displayOrderList}>
                                {/*<span className="sidebar-icon">*/}
                                {/*    <i className="fa fa-code" aria-hidden="true"></i>*/}
                                {/* </span>*/}
                                My orders
                            </a>
                            <a href="#" className="sidebar-link sidebar-link-with-icon pt-20" onClick={this.displayNewOrderForm}> Create new order</a>
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

export default CustomerPage;