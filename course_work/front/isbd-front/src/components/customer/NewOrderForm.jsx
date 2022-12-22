import React from 'react';
import * as events from "events";

class NewOrderForm extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            departure: '',
            destination: '',
            description: '',
            goods: []
        }
    }

    componentDidMount() {
        this.getCities();
    }

    getCities() {
        //TODO: make ajax to server, get json of cities, setState()
    }

    handleDepartureChange = event => {
        this.setState({
            departure: event.target.value
        })
    }
    handleDestinationChange = event => {
        this.setState({
            destination: event.target.value
        })
    }
    handleDescriptionChange = event => {
        this.setState({
            description: event.target.value
        })
    }

    handleAddGood = (event) => {
        let goods = [...this.state.goods];
        let newGood = {}
        goods.push(newGood);
        this.setState({goods: goods});
    }

    handleSubmit = event => {
        console.log(this.state);
        event.preventDefault();
    }

    renderGoods = () => {
        const goodList = this.state.goods.map(
            good => (
                <div></div>
            )
        )
    }

    render() {
        return (
            <div className="content">
                <form onSubmit={this.handleSubmit}
                      className="mw-full">

                    <div className="form-row row-eq-spacing">
                        <div className="col">
                            <div className="form-group">
                                <label htmlFor="departure" className="required">Departure:</label>

                                <select className="form-control" id="departure" required="required"
                                        value={this.state.departure}
                                        onChange={this.handleDepartureChange}>
                                    <option value="" selected="selected" disabled="disabled">From city:
                                    </option>
                                    <option value="front-end">Capital Knot City</option>
                                    <option value="back-end">South Knot City</option>
                                    <option value="full-stack">West Knot City</option>
                                    {/*    {getCities}*/}
                                </select>
                            </div>
                        </div>
                        <div className="col">
                            <div className="form-group">
                                <label htmlFor="destination" className="required">Destination</label>
                                <select className="form-control" id="destination" required="required"
                                        value={this.state.destination}
                                        onChange={this.handleDestinationChange}>
                                    <option value="" selected="selected" disabled="disabled">From city:
                                    </option>
                                    <option value="front-end">Capital Knot City</option>
                                    <option value="back-end">South Knot City</option>
                                    <option value="full-stack">West Knot City</option>
                                    {/*    {getCities}*/}
                                </select>
                            </div>
                        </div>
                    </div>

                    <div className="form-group">
                        <label htmlFor="description">Description</label>
                        <textarea className="form-control" id="description"
                                  placeholder="Write a short description about your order"
                                  value={this.state.description}
                                  onChange={this.handleDescriptionChange}>
                        </textarea>
                    </div>

                    <div className="content bg-dark p-20 m-0 mb-20">

                        <button className="btn btn-primary" type="button" onClick={this.handleAddGood}>Add good</button>
                    </div>


                    <input className="btn btn-primary" type="submit" value="Submit"/>
                </form>
            </div>
        );
    }

}

export default NewOrderForm;