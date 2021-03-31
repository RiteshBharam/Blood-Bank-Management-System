import React, { Component } from 'react';

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
import ArrowUpwardIcon from '@material-ui/icons/ArrowUpward';
import PeopleIcon from '@material-ui/icons/PeopleOutlined';
import axios from 'axios';


const useStyles = makeStyles((theme) => ({
  root: {
    height: '100%'
  },
  avatar: {
    backgroundColor: colors.green[600],
    height: 56,
    width: 56
  },
  differenceIcon: {
    color: colors.green[900]
  },
  differenceValue: {
    color: colors.green[900],
    marginRight: theme.spacing(1)
  }
}));


class ANegativeCounts extends Component {
  constructor(props) {
    super(props);
    this.state = {
        bloodgroups: []
    }
}
  componentDidMount() {
    console.log("before.....")
    axios.get('http://localhost:8080/bloodbank/inventory/blood/{bloodType}?bloodType=ANEGATIVE').then(resp => {
      console.log("Blood list is...", resp)
      this.setState({
          bloodgroups: resp.data
      })
  })

  }
  render() {
    // const classes = useStyles();

    return (
      <Card
      // className={clsx(classes.root, className)}
      // {...rest}
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
                A-
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
                <PeopleIcon />
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
      </Card>
    );
  }
}

export default ANegativeCounts;
