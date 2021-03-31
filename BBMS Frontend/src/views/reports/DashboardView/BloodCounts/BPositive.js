import React, { Component } from 'react';
import clsx from 'clsx';
import PropTypes from 'prop-types';
import {
  Avatar,
  Box,
  Card,
  CardContent,
  Grid,
  Typography,
  colors,
  makeStyles
} from '@material-ui/core';
import ArrowDownwardIcon from '@material-ui/icons/ArrowDownward';
import MoneyIcon from '@material-ui/icons/Money';
import axios from 'axios';

const useStyles = makeStyles((theme) => ({
  root: {
    height: '100%'
  },
  avatar: {
    backgroundColor: colors.red[600],
    height: 56,
    width: 56
  },
  differenceIcon: {
    color: colors.red[900]
  },
  differenceValue: {
    color: colors.red[900],
    marginRight: theme.spacing(1)
  }
}));
class BPositiveCounts extends Component {
  constructor(props) {
    super(props);
    this.state = {
        bloodgroups: []
    }
}
  componentDidMount() {
    console.log("before.....")
    axios.get('http://localhost:8080/bloodbank/inventory/blood/{bloodType}?bloodType=BPOSITIVE').then(resp => {
      console.log("Blood list is...", resp)
      this.setState({
          bloodgroups: resp.data
      })
  })

  }
  render() {
    return (<Card

    >
      <CardContent>
        <Grid
          container
          justify="space-between"
          spacing={3}
        >
          <Grid item>
            <Typography
              color="textSecondary"
              gutterBottom
              variant="h3"
            >
              B+
            </Typography>
            {this.state.bloodgroups.count ? 
            
            <Typography
              color="textPrimary"
              variant="h3"
            >
              {this.state.bloodgroups.count}
            </Typography>
            : <Typography
              color="textPrimary"
              variant="h3"
            >
            0
            </Typography>
          }
          </Grid>
          <Grid item>
            <Avatar className={useStyles.avatar}>
              <MoneyIcon />
            </Avatar>
          </Grid>
        </Grid>
        <Box
          mt={2}
          display="flex"
          alignItems="center"
        >

        </Box>
      </CardContent>
    </Card>);
  }
}

export default BPositiveCounts;

