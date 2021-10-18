#!/bin/bash

echo 'start ./start.sh'

set -xe \
&& sudo rm -rf /home/ec2-user/coming-m-api/* \
&& mkdir /home/ec2-user/coming-m-api/build \
&& mkdir /home/ec2-user/coming-m-api/build/libs \
&& cp -rf /home/ec2-user/ComingMApiTemp/* /home/ec2-user/coming-m-api/build/libs \
&& sudo service coming-m-api start