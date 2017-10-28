var HOST = 'http://localhost:8080';
 
var agent = require('supertest').agent(HOST);
var assert = require('assert');
 
 
describe('Simple API test', function() {
 
    it('should return an empty list', function(done) {
        var req = [];
 
        agent.get('/tasks')
            .expect(200)
 //           .set('Content-Type', 'application/json')
  //          .send(req)
            .end((err, res) => {
                assert.ok(res.body.length === 0);
                console.log(res.body);
                done();
            });
    });


    it('should return a list with one task', function(done) {
        var req = {"nombre":"task prueba","descripcion":"task para test mochajs"};


            agent.post('/task')
            .expect(200)
            .set('Content-Type', 'application/json')
            .send(req)
            .end((err, res) => {
    //            assert.ok(res.body.length === 0);
     //           console.log(res.body);
                done();
            });
        
 

                setTimeout(function(){

        agent.get('/tasks')
            .expect(200)
            .end((err,res)=>{
                assert.ok(res.body.length == 1);
                console.log(res.body);
                done();
            });
            },300);
    });



});