import React, { useState } from 'react';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core';
import axios from 'axios';
import { Alert } from '@material-ui/lab';
import { useNavigate } from 'react-router-dom';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },

    },
    button: {
        width: '30%',
        /* Center vertically and horizontally */
        position: 'relative',
        top: '20%',
        left: '35%',
        margin: '-25px 0 0 - 25px'
    }
}));


const DonateButton = (props) => {
    const [successMessage, setSuccess] = useState(false);
    const navigate = useNavigate();
    const [errorMessage, setError] = useState(false);
    const [home, setHome] = useState(false);
    const classes = useStyles();
    console.log("donate data is....", props.data)


    const donateBlood = (data) => {
        console.log("hiiiiiiiiiiiiii", data)


        // axios.get('https://jsonplaceholder.typicode.com/todos/1').then(resp=>{
        //     console.log("here is resp...",resp)
        // })

        //"http://localhost:8080/donor/"+data.donorId+"/donate/1"
        axios.get("https://jsonplaceholder.typicode.com/todos/1").then(resp => {
            console.log("log in resp  is...", resp)
            if (resp.status == 200) {
                setSuccess(true)
                setTimeout(() => {
                    setHome(true)
                }, 4000)
            }
            else {
                setError(true)
            }
        });
    }

    if (home) {
        navigate('/', { replace: true });
    }

    return (
        <div className={classes.root}>
            {successMessage ? (
                <>
                    <Alert severity="success">Donation Successful !!</Alert>
                </>
            ) : null}
            {errorMessage ? (
                <>
                    <Alert severity="error">Donation Unsuccessful !!</Alert>
                </>
            ) : null}
            <div>
                <Button className={classes.button} onClick={async () => {await donateBlood(props.data.donorId) }} variant="contained" color="primary">
                    Donate
      </Button>
            </div>
        </div>
    );
}


export default DonateButton;