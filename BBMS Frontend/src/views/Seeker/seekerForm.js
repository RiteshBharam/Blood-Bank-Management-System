import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import { Button, Tooltip, Typography } from "@material-ui/core";
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import NativeSelect from '@material-ui/core/NativeSelect';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { Alert, AlertTitle } from "@material-ui/lab";
import Submit from 'src/views/Seeker/submit';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: "center",
        color: theme.palette.text.secondary
    },
    paper1: {
        padding: theme.spacing(2),
        textAlign: "center"
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120
    },
    selectEmpty: {
        marginTop: theme.spacing(2)
    },
    detailtable: {
        width: "80%",
        padding: "10% 0 0",
        margin: "auto"
    },
    typographyStyle: {
        color: "#000000",
        textAlign: "center",
        opacity: "1",
        font: "normal normal bold 32px/64px Calibri",
        fontFamily: "Roboto",
        color: '#707070',
        marginBottom: "1.5rem",
        marginTop: "1.5rem"
    }
}));

export default function CenteredGrid() {
    const classes = useStyles();
    const [formData, setFormData] = useState({});
    const navigate = useNavigate();
    const [bloodgroup, setBloodGroup] = React.useState('');
    const [successMessage, setSuccess] = useState(false);
    const [ContactPage, setContact] = useState(false);

    const handleChangeBloodGroup = (event) => {
        setBloodGroup(event.target.value);
    };
    const handleChange = (event) => {
        const { name, value } = event.target;
        setFormData((formData) => ({
            ...formData,
            [name]: value
        }));
        let d = Object.values(formData);
        console.log("seeker data >>>>>>>>>>>>>>>>", d);

    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const data={
            "address": formData.address,
            "bloodGroup": bloodgroup,
            "contactNumber": formData.phone,
            "email": formData.email,
            "name": formData.name,
            "quantity": formData.quantity,
            "reason": formData.reason,
            "requestStatus": "NEW",
            "seekerId": 0}
            console.log("Seeker data is present......",data)
        fetch(`http://localhost:8080/request/blood/${bloodgroup}`, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json; charset=utf8'
            },
           body:JSON.stringify(data)
            
            
        }).then(response => {
            if (response.status === 200) {
              console.log("final resp....",response)
              setSuccess(true)
              setTimeout(() => {
                setContact(true);
              }, 2000)
            }
          
           
        })
    }
    if(ContactPage){
        return (<Submit/>)
    }
    return (

        <div className={classes.root}>
            <br />
            <Typography className={classes.typographyStyle}>
                Request For Blood
            </Typography>
            <Grid container spacing={3}>
                <Grid item xs={12}></Grid>
                <Grid item xs={5} className={classes.detailtable}>
                    <Paper className={classes.paper}>
                        <div
                            style={{
                                width: "70%",
                                marginLeft: "15%"
                            }}
                        >
                            <form
                                noValidate={true}
                                style={{ display: "grid", padding: "1rem" }}
                                onSubmit={handleSubmit}

                            >
                                <TextField
                                    id="standard-basic"
                                    label="Name or Organization"
                                    onChange={handleChange}
                                    name="name"
                                />
                                <TextField
                                    id="standard-basic"
                                    label="Address"
                                    onChange={handleChange}
                                    name="address"
                                />
                                <TextField
                                    id="standard-basic"
                                    label="Email"
                                    onChange={handleChange}
                                    name="email"
                                />
                                <TextField
                                    id="standard-basic"
                                    label="Phone No"
                                    onChange={handleChange}
                                    name="phone"
                                    onInput={(e) => {
                                        e.target.value = Math.max(0, parseInt(e.target.value)).toString().slice(0, 10)
                                    }}
                                />

                                <TextField
                                    id="standard-basic"
                                    label="Age"
                                    type="Number"
                                    name="age"
                                />

                                <Select
                                    value={bloodgroup}
                                    onChange={handleChangeBloodGroup}
                                    displayEmpty
                                    className={classes.selectEmpty}
                                    inputProps={{ 'aria-label': 'Without label' }}
                                >
                                    <MenuItem value="" disabled>
                                        Blood Group
                                    </MenuItem>
                                    <MenuItem value="OPOSITIVE">O+</MenuItem>
                                    <MenuItem value="ONEGATIVE">O-</MenuItem>
                                    <MenuItem value="APOSITIVE">A+</MenuItem>
                                    <MenuItem value="ANEGATIVE">A-</MenuItem>
                                    <MenuItem value="BPOSITIVE">B+</MenuItem>
                                    <MenuItem value="BNEGATIVE">B-</MenuItem>
                                    <MenuItem value="ABPOSITIVE">AB+</MenuItem>
                                    <MenuItem value="ABNEGATIVE">AB-</MenuItem>


                                </Select>
                                <TextField
                                    id="standard-basic"
                                    label="Quantity in Unit"
                                    type="Number"
                                    name="quantity"
                                    onChange={handleChange}

                                />
                                <TextField
                                    id="standard-basic"
                                    label="Reason"
                                    onChange={handleChange}
                                    name="reason"
                                />
                                <br />
                                {successMessage ? (
                    <>
                      <Alert severity="success">Request Submitted Successfully !!</Alert>
                    </>
                  ) : null}
                                <Button
                                    type="submit"
                                    variant="contained"
                                    color="primary"

                                >
                                    Request
                      </Button>
                      
                                <br />
                            </form>

                        </div>
                    </Paper>
                </Grid>
            </Grid>
        </div>
    );


}