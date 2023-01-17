import React, {Component} from 'react';
import axios from "axios";
import StatusButtons from "./StatusButtons";

class CourierAvailableOrderTable extends Component {

    constructor(props) {
        super(props);
        this.state = {
            orders: []
        }
    }

    componentDidMount() {

        axios.get('http://localhost:8080/get-all-orders-for-view')
            .then(res => {
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
            <div className="card">
                <table className="table text-white">
                    <thead>
                    <tr>
                        <th>Status</th>
                        <th>Departure</th>
                        <th>Destination</th>
                        <th className="text-right">Description</th>
                        <th className="text-right pr-20">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.orders.map(
                            order => (
                                <tr key={order.order_id}>
                                    <td>{order.status}</td>
                                    <td>{order.departure_point}</td>
                                    <td>{order.destination_point}</td>
                                    <td className="text-right">{order.description}</td>
                                    <td className="text-right">
                                        <StatusButtons status={order.status} order_id={order.order_id} mode="order"></StatusButtons>
                                    </td>
                                </tr>
                            )
                        )
                    }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default CourierAvailableOrderTable;