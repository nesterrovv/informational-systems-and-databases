import React, {useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import GoodFormListElement from "./GoodFormListElement";
import axios from "axios";

function NewOrderForm() {

    const dispatch = useDispatch();
    let goods = useSelector(state => state.goods.goods);

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
        setDestination(event.target.value);
    }
    const handleDescriptionChange = event => {
        setDescription(event.target.value);
    }

    const handleAddGood = (event) => {
        dispatch({type: "ADD_GOOD"});
        setGoods([...goods]);
    }

    const updateGoodList = (event) => {
        setGoods([...goods]);
    }

    const handleSubmit = event => {
        if (goods.length == 0) {
            alert("Add goods to the order!")
            event.preventDefault()
            return
        }
        let order = {
            //TODO: store customer_id as global variable
            customer_id: 9,
            status: "WAITING",
            departure_point: departure,
            destination_point: destination,
            description: description,
            goods: []
        }

        let isValidationSuccess = goods.every(good => {
            if (!validateDecimal(good.length)) {
                alert("Length must be positive number!")
                return false
            }
            if (!validateDecimal(good.width)) {
                alert("Width must be positive number!")
                return false
            }
            if (!validateDecimal(good.height)) {
                alert("Height must be positive number!")
                return false
            }
            if (!validateDecimal(good.weight)) {
                alert("Weight must be positive number!")
                return false
            }

            order.goods.push({
                status: "WAITING",
                length: good.length,
                width: good.width,
                height: good.height,
                weight: good.weight,
                description: good.description
            })

            return true
        })

        if (isValidationSuccess) {
            console.log("Order: ", order);
            axios.post("http://localhost:8080/create-order-via-dto", order)
                .then((res) => {
                        console.log(res);
                        alert("Order created successfully!");
                    }
                )
                .catch(err => {
                    alert(err);
                    console.log(err);
                })
        }
        event.preventDefault();
    }

    function validateDecimal(value) {
        return (value - 0) == value && ('' + value).trim().length > 0 && value > 0

    }

    const renderGoods = () => {
        console.log("renderGoods: ", goods);
        const goodsRenderList = stateGoods.map(
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
                                <option value="Capital Knot City">Capital Knot City</option>
                                <option value="Central Knot City">Central Knot City</option>
                                <option value="Middle Knot City">Middle Knot City</option>
                                <option value="Mountain Knot City">Mountain Knot City</option>
                                <option value="Port Knot City">Port Knot City</option>
                                <option value="South Knot City">South Knot City</option>
                                <option value="Edge Knot City">Edge Knot City</option>
                                <option value="Lake Knot City">Lake Knot City</option>
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
                                <option value="Capital Knot City">Capital Knot City</option>
                                <option value="Central Knot City">Central Knot City</option>
                                <option value="Middle Knot City">Middle Knot City</option>
                                <option value="Mountain Knot City">Mountain Knot City</option>
                                <option value="Port Knot City">Port Knot City</option>
                                <option value="South Knot City">South Knot City</option>
                                <option value="Edge Knot City">Edge Knot City</option>
                                <option value="Lake Knot City">Lake Knot City</option>
                                {/*    {getCities}*/}
                            </select>
                        </div>
                    </div>
                </div>

                <div className="form-group">
                    <label htmlFor="description" className="required">Description</label>
                    <textarea className="form-control" id="description"
                              placeholder="Write a short description about your order"
                              value={description}
                              required="required"
                              onChange={handleDescriptionChange}>
                        </textarea>
                </div>

                <div className="content bg-dark p-20 m-0 mb-20">
                    {/*{renderGoods}*/}
                    <ul>
                        {stateGoods.map(
                            good => (
                                <GoodFormListElement id={good.id} updateFunction={updateGoodList}/>
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