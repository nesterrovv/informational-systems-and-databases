import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";

const GoodFormListElement = (props) => {

    const dispatch = useDispatch();
    const goods = useSelector(state => state.goods.goods);

    const [length, setLength] = useState(undefined);
    const [width, setWidth] = useState(undefined);
    const [height, setHeight] = useState(undefined);
    const [weight, setWeight] = useState(undefined);
    const [description, setDescription] = useState('');

    useEffect(() => {
        updateValues();
    }, [length, width, height, weight, description]);

    const updateValues = () => {
        dispatch({
            type: "UPDATE_GOOD",
            payload: {
                id: props.id,
                length: length,
                width: width,
                height: height,
                weight: weight,
                description: description
            }
        })
    }
    const handleLengthChange = event => {
        setLength(event.target.value);
        updateValues();
    }
    const handleWidthChange = event => {
        setWidth(event.target.value);
    }
    const handleHeightChange = event => {
        setHeight(event.target.value);
    }
    const handleWeightChange = event => {
        setWeight(event.target.value);
    }
    const handleDescriptionChange = event => {
        setDescription(event.target.value);
    }

    const handleDeleteGood = () => {
        dispatch({type: "DELETE_GOOD", payload: props.id});
        props.updateFunction();
    }

    return (
        <li key={props.id}>
            <div className="card">
                <div className="form-group">
                    <div className="form-row row-eq-spacing">
                        <div className="col">
                            <label htmlFor="weight" className="required">Weight:</label>
                            <input type="text" className="form-control" id="weight" placeholder="Weight"
                                   pattern="[0-9]*[\.\,]?[0-9]*$" inputMode="decimal" required="required"
                                   value={weight}
                                   onChange={handleWeightChange}
                            />
                        </div>
                        <div className="col"></div>
                        <div className="col">
                            <div className="d-flex flex-row-reverse align-items-center h-full">
                                <button className="btn btn-danger" type="button" onClick={handleDeleteGood}>Remove
                                </button>
                            </div>
                        </div>
                    </div>
                    <div className="form-row row-eq-spacing">
                        <div className="col">
                            <label htmlFor="length" className="required">Length:</label>
                            <input type="text" className="form-control" id="length" placeholder="Length"
                                   pattern="[0-9]*[\.\,]?[0-9]*$" inputMode="decimal" required="required"
                                   value={length}
                                   onChange={handleLengthChange}
                            />
                        </div>
                        <div className="col">
                            <label htmlFor="width" className="required">Width:</label>
                            <input type="text" className="form-control" id="width" placeholder="Width"
                                   pattern="[0-9]*[\.\,]?[0-9]*$" inputMode="decimal" required="required"
                                   value={width}
                                   onChange={handleWidthChange}
                            />
                        </div>
                        <div className="col">
                            <label htmlFor="height" className="required">Height:</label>
                            <input type="text" className="form-control" id="height" placeholder="Height"
                                   pattern="[0-9]*[\.\,]?[0-9]*$" inputMode="decimal" required="required"
                                   value={height}
                                   onChange={handleHeightChange}
                            />
                        </div>
                    </div>
                    <div className="form-group">
                        <label className="required" htmlFor="description">Description</label>
                        <textarea className="form-control" id="description"
                                  placeholder="Write a short description about your order"
                                  value={description}
                                  required="required"
                                  onChange={handleDescriptionChange}>
                        </textarea>
                    </div>
                </div>
            </div>
        </li>
    );
};

export default GoodFormListElement;