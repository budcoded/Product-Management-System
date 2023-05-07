FROM node:alpine
WORKDIR /app
COPY ./package.json /app
RUN npm install
RUN npm cache clean --force
COPY . /app
CMD [ "npm", "start" ]