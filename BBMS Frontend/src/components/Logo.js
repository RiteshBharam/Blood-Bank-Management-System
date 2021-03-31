import React from 'react';
import { Typography } from '@material-ui/core'

const Logo = (props) => {
  return (
    <Typography style={{
      flexGrow: '1',
      cursor: "pointer",
      color: '#ffffff',
      font: "normal normal bold 32px/64px Calibri",
      fontFamily: 'Roboto'
    }}>
     Blood Bank Management App
    </Typography>
    // <img
    //   alt="Logo"
    //   src="/static/logo.svg"
    //   {...props}
    // />
  );
};

export default Logo;
