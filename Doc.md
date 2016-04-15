---
layout: post
title: Containers
permalink: /Containers1.md/
---

Containers Basic Tutorial
===================


Documents
-------------
Download Docker and Update CF commands
Files

 - PPT -  [Click me](https://github.com/alphawolf101/alphawolf101.github.io/blob/master/Containers.ppt)
 
 - Docker - [Click me](https://github.com/docker/toolbox/releases/download/v1.10.0/DockerToolbox-1.10.0.exe)
 

Make sure CF is installed in your pc if yes issue the command in your CMD

> Command

> 64 bit

>- cf install-plugin https://static-ice.ng.bluemix.net/ibm-containers-windows_x64.exe
> 
> 32 bit
> 
>  - cf install-plugin https://static-ice.ng.bluemix.net/ibm-containers-windows_x86.exe

> 
#### </i> Installing Docker

 1. **Run** DockerToolbox.exe file
 2. Click next
 3. You can **unselect kitematic** since kitematic will not be used in this tutorial 
 4. Click next
 5. Click **Add docker to path**
 6. Click next
 7. Install Docker

Docker is a third party software that is needed to push and pull images to IBM containers the **CF IC** commands in this tutorial are the IBM equivalent to the docker commands that are locally issued   

#### </i> Setting up your Image 

Open a CMD interface and type the following

> cf login -a api.ng.bluemix.net
> cf ic login

We will also need to enter detail regarding our images 

After login enter the following command

> - cf ic namespace set "new_name"

> *a default name space is required to run IBM Containers this is separate from the dev space

Run the following command to see the list of images 

>- cf ic images

Select from the images 

> For now we will go with IBMLIBERTY for this tutorial
>
 - cf ic run -p 9080 --name test registry.ng.bluemix.net/ibmliberty

Next we will need to bind an IP addres to the Container

>  - cf ic ip request

IBM will provide you with your own public address 

Next is Binding the IP address to your container

> - cf ic ip bind <public_IP_address> <container_name_or_id>

You can now visit your Container on the web by going to 

> http://[public_IP_address]:9080


