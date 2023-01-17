import React, {Component} from 'react';
import CustomerOrder from "./CustomerOrder";
import axios from "axios";

class CustomerOrderList extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            orders:[]
        }
    }
    componentDidMount() {
        //TODO: get id from globally stored value
        axios.get('http://localhost:8080/get-all-orders-for-view-by-customer', {params: {id: 1}})
            .then(res => {
                console.log(res.data)
                this.setState({
                    orders: res.data
                })
            })
            .catch(err => {
                console.log(err)
            })
    }

    render() {
        return (
            <div>
                {this.state.orders.map(
                    order => (
                        //TODO: set departure & description names, not id
                        //TODO:
                        <CustomerOrder order_status={order.status} departure={order.departure_point}
                                       destination={order.destination_point}
                                       goods={order.goods}
                                       description={order.description}></CustomerOrder>
                    )
                )}
            </div>
        );
    }
}

export default CustomerOrderList;