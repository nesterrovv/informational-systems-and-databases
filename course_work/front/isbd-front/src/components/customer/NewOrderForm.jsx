import React, {useState} from 'react';
import {useDispatch, useSelector} from "react-redux";

function NewOrderForm() {

    const dispatch = useDispatch();
    const goods = useSelector(state => state.goods.goods);

    const [departure, setDeparture] = useState('');
    const [destination, setDestination] = useState('');
    const [description, setDescription] = useState('');
    const [stateGoods, setGoods] = useState([]);
    const getCities = () => {
        //TODO: make ajax to server, get json of cities, setState()
    }

    const handleDepartureChange = event => {
        setDeparture(event.target.value);
    }
    const handleDestinationChange = event => {
        // this.setState({
        //     destination: event.target.value
        // })
        setDestination(event.target.value);
    }
    const handleDescriptionChange = event => {
        // this.setState({
        //     description: event.target.value
        // })
        setDescription(event.target.value);
    }

    const handleAddGood = (event) => {
        dispatch({type: "ADD_GOOD"});
        console.log("goods", goods);
        setGoods([...goods]);
        console.log("stateGoods", stateGoods);
    }

    const handleSubmit = event => {
        console.log(departure, destination, description, stateGoods);
        event.preventDefault();
    }

    const renderGoods = () => {
        console.log("renderGoods: ", goods);
        const goodsRenderList =  stateGoods.map(
            good => (
                <div>{good.id}</div>
            )
        );
        return goodsRenderList;
    }

    return (
        <div className="content">
            <form onSubmit={handleSubmit}
                  className="mw-full">

                <div className="form-row row-eq-spacing">
                    <div className="col">
                        <div className="form-group">
                            <label htmlFor="departure" className="required">Departure:</label>

                            <select className="form-control" id="departure" required="required"
                                    value={departure}
                                    onChange={handleDepartureChange}>
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
                                    value={destination}
                                    onChange={handleDestinationChange}>
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
                              value={description}
                              onChange={handleDescriptionChange}>
                        </textarea>
                </div>

                <div className="content bg-dark p-20 m-0 mb-20">
                    {/*{renderGoods}*/}
                    <ul>
                        {stateGoods.map(
                            good => (
                                <li key={good.id}>{good.id}</li>
                            )
                        )}
                    </ul>
                    <button className="btn btn-primary" type="button" onClick={handleAddGood}>Add good</button>
                </div>


                <input className="btn btn-primary" type="submit" value="Submit"/>
            </form>
        </div>
    );
}

export default NewOrderForm;