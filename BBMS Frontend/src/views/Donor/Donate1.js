import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core';
import axios from 'axios';
import {Alert} from '@material-ui/lab';
import HomePage from '../Home/home';
 

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
      
    },
    button: {
        width: '30%',
        /* Center vertically and horizontally */
        position: 'absolute',
        top: '20%',
        left: '35%',
        margin: '-25px 0 0 - 25px'
    }
}));

class DonateButton extends Component{
    constructor(props) {
        super(props);
        this.state = {
            successMessage:false,
            errorMessage:false,
            home:false,
            donorId:props.data.donorId
        }
    }
         donateBlood(data){
            //"http://localhost:8080/donor/"+data.donorId+"/donate/1"
            console.log("reached here",data)
            axios.post("http://localhost:8080/donor/"+data+"/donate/1").then(resp => {
                console.log("line 40 donate1", resp)
                if(resp.status==200)
                {
                    this.setState({
                        successMessage:true,
                    })
                    setTimeout(()=>{
                        this.setState({
                            home:true
                        })
                    },5000)
                }
                else{
                    this.setState({erroMessage:true})
            }
        });
    }
    
    render(){
        if(this.state.home){
            return(<HomePage/>)
        }
        return (
            <div className={useStyles.root}>
             {this.state.successMessage ? (
                <>
                  <Alert severity="success">Donation Successful !!</Alert>
                </>
              ) : null}
              {this.state.errorMessage ? (
                <>
                  <Alert severity="error">Donation Unsuccessful !!</Alert>
                </>
              ) : null}
                <Button onClick={()=>{this.donateBlood(this.state.donorId)}} className={useStyles} variant="contained" color="primary">
                    Donate
          </Button>
            </div>)
        
    }
}

export default DonateButton

