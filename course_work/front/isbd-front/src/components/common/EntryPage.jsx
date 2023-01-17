import React, {useEffect, useState} from 'react';
import GoodFormListElement from "../customer/GoodFormListElement";

const EntryPage = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    //"login", "register"
    const [mode, setMode] = useState('');
    //"courier", "customer"
    const [status, setStatus] = useState('');

    const message = {
        username: username,
        password: password,
        mode: mode,
        status: status
    }

    useEffect(() => {
        updateFields();
        sendRequest();
    }, [mode, status]);

    const updateFields = () => {
        message.username = username;
        message.password = password;
        message.mode = mode;
        message.status = status;
    }
    const handleUsernameChange = event => {
        setUsername(event.target.value);
    }

    const handlePasswordChange = event => {
        setPassword(event.target.value);
    }

    const sendRequest = () => {
        updateFields();
        console.log("send request", message);
    }


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
                                       placeholder="Enter your username"
                                       value={username}
                                       onChange={handleUsernameChange}
                                >
                                </input>
                            </div>
                            <div className="form-group pb-20">
                                <label htmlFor="description">Password:</label>
                                <input className="form-control" id="password"
                                       placeholder="Enter your password" type="password"
                                       value={password}
                                       onChange={handlePasswordChange}
                                >
                                </input>
                            </div>
                            <div className="border-top pt-20 mt-20">
                                <div className="form-row row-eq-spacing pt-20">
                                    <div className="col">
                                        <input className="btn btn-success w-full" type="submit"
                                               value="Register as customer"
                                               onClick={(event) => {
                                                    setMode("register");
                                                    setStatus("customer");
                                                    event.preventDefault();
                                               }
                                               }
                                        />
                                    </div>
                                    <div className="col">
                                        <input className="btn btn-primary w-full" type="submit"
                                               value="Login as customer"
                                               onClick={(event) => {
                                                   setMode("login");
                                                   setStatus("customer");
                                                   event.preventDefault();
                                               }
                                               }
                                        />
                                    </div>
                                </div>
                            </div>
                            <div className="form-row row-eq-spacing">
                                <div className="col">
                                    <input className="btn btn-success w-full m-auto" type="submit"
                                           value="Register as courier"
                                           onClick={(event) => {
                                               setMode("register");
                                               setStatus("courier");
                                               event.preventDefault();
                                           }
                                           }
                                    />
                                </div>
                                <div className="col">
                                    <input className="btn btn-primary w-full" type="submit"
                                           value="Login as courier"
                                           onClick={(event) => {
                                               setMode("login");
                                               setStatus("courier");
                                               event.preventDefault();
                                           }
                                           }
                                    />
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