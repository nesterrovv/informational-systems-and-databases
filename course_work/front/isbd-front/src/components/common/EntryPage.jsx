import React from 'react';
import GoodFormListElement from "../customer/GoodFormListElement";

const EntryPage = () => {
    return (
        <div className="page-wrapper">
            <div className="content-wrapper">
                {/*TODO*/}
                <div className="d-flex justify-content-center h-full">
                    <div className="card w-450 h-350 align-self-center">
                        <form onSubmit=""
                              className="mw-full">
                            <div className="form-group">
                                <label htmlFor="description">Username:</label>
                                <input className="form-control" id="username"
                                       placeholder="Enter your username">
                                </input>
                            </div>
                            <div className="form-group pb-20">
                                <label htmlFor="description">Password:</label>
                                <input className="form-control" id="password"
                                       placeholder="Enter your password" type="password">
                                </input>
                            </div>
                            <div className="border-top pt-20 mt-20">
                                <div className="form-row row-eq-spacing pt-20">
                                    <div className="col">
                                        <input className="btn btn-success w-full" type="submit"
                                               value="Register as customer"/>
                                    </div>
                                    <div className="col">
                                        <input className="btn btn-primary w-full" type="submit"
                                               value="Login as customer"/>
                                    </div>
                                </div>
                            </div>
                            <div className="form-row row-eq-spacing">
                                <div className="col">
                                    <input className="btn btn-success w-full m-auto" type="submit"
                                           value="Register as courier"/>
                                </div>
                                <div className="col">
                                    <input className="btn btn-primary w-full" type="submit" value="Login as courier"/>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EntryPage;