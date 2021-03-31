import React, { Component } from 'react';
import {
    Container,
    Grid,
    makeStyles,
    Card,
    CardContent,
    Typography,
} from '@material-ui/core';
import Page from 'src/components/Page';
import { Link as RouterLink, useNavigate } from 'react-router-dom';


const useStyles = makeStyles((theme) => ({
    root: {
        backgroundColor: theme.palette.background.dark,
        minHeight: '100%',
        paddingBottom: theme.spacing(3),
        paddingTop: theme.spacing(3)
    },
    admincard: {
        backgroundColor:'red'
    }
}));

const HomePage = () => {
    const navigate = useNavigate();

    return (
        <Page
            className={useStyles.root}
            title="Home"
        >
            <Container maxWidth={false}>
                <Grid
                    container
                    spacing={5}
                >
                    <Grid
                        item
                        lg={4}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <Card
                        className={useStyles.admincard}

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
                                            style={{ textAlign: 'center', cursor: 'pointer' }}
                                            onClick={() => {
                                                navigate('/login');
                                            }}
                                        >
                                            ADMIN
                                        </Typography>

                                    </Grid>

                                </Grid>

                            </CardContent>
                        </Card>
                    </Grid>

                    <Grid
                        item
                        lg={4}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <Card

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
                                            style={{ textAlign: 'center', cursor: 'pointer' }}
                                            onClick={() => {
                                                navigate('/donorlogin');
                                            }}
                                        >
                                            DONOR
                                        </Typography>

                                    </Grid>

                                </Grid>

                            </CardContent>
                        </Card>
                    </Grid>

                    <Grid
                        item
                        lg={4}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <Card

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
                                            style={{ textAlign: 'center', cursor: 'pointer' }}
                                            onClick={() => {
                                                navigate('/seekerform');
                                            }}
                                        >
                                            SEEKER
                                        </Typography>

                                    </Grid>

                                </Grid>

                            </CardContent>
                        </Card>
                    </Grid>


                </Grid>
            </Container>
        </Page>
    );
}


export default HomePage;