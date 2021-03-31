import React, { Component } from 'react';
import {
    Container,
    Grid,
    makeStyles
} from '@material-ui/core';
import Page from 'src/components/Page';
import DonationCard from './DonationCard';
import OPositiveCounts from './BloodCounts/OPositive';
import ONegativeCounts from './BloodCounts/ONegative';
import APositiveCounts from './BloodCounts/APositive'
import ANegativeCounts from './BloodCounts/ANegative'
import BPositiveCounts from './BloodCounts/BPositive'
import BNegativeCounts from './BloodCounts/BNegative'
import ABPositiveCounts from './BloodCounts/ABPositive'
import ABNegativeCounts from './BloodCounts/ABNegative'


const useStyles = makeStyles((theme) => ({
    root: {
        backgroundColor: theme.palette.background.dark,
        minHeight: '100%',
        paddingBottom: theme.spacing(3),
        paddingTop: theme.spacing(3)
    }
}));

class DashboardView extends Component {
   
    render() {
        return (<Page
            className={useStyles.root}
            title="Dashboard"
        >
            <Container maxWidth={false}>
                <Grid
                    container
                    spacing={3}
                >
                    <Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <OPositiveCounts />
                    </Grid>
                    <Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <ONegativeCounts />
                    </Grid>
                    <Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <APositiveCounts />
                    </Grid>
                    <Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <ANegativeCounts />
                    </Grid>
                    <Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <BPositiveCounts />
                    </Grid>
                    <Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <BNegativeCounts />
                    </Grid><Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <ABPositiveCounts />
                    </Grid>
                    <Grid
                        item
                        lg={3}
                        sm={6}
                        xl={3}
                        xs={12}
                    >
                        <ABNegativeCounts />
                    </Grid>


                    <Grid
                        item
                        lg={12}
                        md={12}
                        xl={9}
                        xs={12}
                    >
                        <DonationCard />
                    </Grid>



                </Grid>
            </Container>
        </Page>

        );
    }
}

export default DashboardView;