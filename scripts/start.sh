#!/bin/bash

echo 'start start.sh'

set -xe \
&& sudo rm -rf /home/ec2-user/coming-m-api/* \
&& cp -rf /home/ec2-user/ComingMApiTemp/* /home/ec2-user/coming-m-api \
&& sudo service coming-m-api start